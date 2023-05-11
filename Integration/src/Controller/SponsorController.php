<?php

namespace App\Controller;

use App\Entity\Sponsor;
use App\Form\SponsorType;
use App\Repository\SponsorRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Knp\Component\Pager\PaginatorInterface;
use Twilio\Rest\Client;
use Dompdf\Dompdf;
use Dompdf\Options;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\Serializer\Normalizer\NormalizerInterface;
use Symfony\Component\Serializer\Serializer;
use Symfony\Component\Serializer\SerializerInterface;
use Symfony\Component\HttpFoundation\ResponseHeaderBag;
#[Route('/sponsor')]
class SponsorController extends AbstractController
{
    #[Route('/pdfjson', name: 'app_pdf_json', methods: ['GET'])]
    public function pdfjson(SponsorRepository $SponsorRepository)
    {
        $pdfOptions = new Options();
        $pdfOptions->set('defaultFont', 'Arial');
    
        $dompdf = new Dompdf($pdfOptions);
        $html = $this->renderView('sponsor/pdf.html.twig', [
            'sponsors' => $SponsorRepository->findAll(),
        ]);
    
        $dompdf->loadHtml($html);
        $dompdf->setPaper('A4', 'portrait');
    
        $dompdf->render();
        $pdfContent = $dompdf->output();
    
        $response = new Response($pdfContent);
        $response->headers->set('Content-Type', 'application/pdf');
        $response->headers->set('Content-Disposition', $response->headers->makeDisposition(
            ResponseHeaderBag::DISPOSITION_ATTACHMENT,
            'ListeDesSponsors.pdf'
        ));
    
        return $response;
    }


    #[Route("/allsponsors", name: "app_sponsors")]
    public function displayjson1(SponsorRepository $repo, SerializerInterface $serializer)
    {
        $sponsors = $repo->findAll();
       
        $json = $serializer->serialize($sponsors, 'json', ['groups' => "spon"]);
    
        return new Response($json);
    }
    #[Route("/addsponsor", name: "addsponsorJSON")]
    public function addSponsorJSON(Request $req, NormalizerInterface $Normalizer)
    {
        $em = $this->getDoctrine()->getManager();
    
        $sponsor = new sponsor();
        $sponsor->setIntitule($req->get('intitule'));
        $sponsor->setDureeContrat($req->get('duree_contrat'));
       
        
        $dateDebcStr = $req->get('date_debc');
        
        $dateDebc = \DateTime::createFromFormat('Y-m-d', $dateDebcStr);
        $sponsor->setDateDebc($dateDebc);
        
      
        $dateFincStr=$req->get('date_finc');
        $dateFinc = \DateTime::createFromFormat('Y-m-d', $dateFincStr);
        $sponsor->setDateFinc($dateFinc);
    
        $em->persist($sponsor);
        $em->flush();
        
        $jsonContent = $Normalizer->normalize($sponsor, 'json', ['groups' => 'spon']);
        
       $accountSid = 'ACb3512b1baa60eb994049eea0656f6934';
        $authToken = 'db782f1385c2a4a273b50e7c3222a46f';
        $client = new Client($accountSid, $authToken);

        $message = $client->messages->create(
            '+21621560477', // replace with admin's phone number
            [
                'from' => '+15673343461', // replace with your Twilio phone number
                'body' => 'A new sponsor has been successfully added ' ,
            ]

        );

         // Send SMS notification
        /* $accountSid = 'AC510d9d1f73bc75c99359bdd72b94f7b7';
         $authToken = '1682a1ab583d02c90ec40d8833ff0e2a';
         $client = new Client($accountSid, $authToken);
         $message = $client->messages->create(
             '+21629225165', // replace with admin's phone number
             [
                 'from' => '+13204387642', // replace with your Twilio phone number
                 'body' => 'A new sponsor  has been successfully added.',
             ]
         );*/
        return new Response(json_encode($jsonContent));
    }
  

    
#[Route("/updatesponsor/{id_sponsor}", name: "updatesponsorJSON")]
public function updateSponsorJSON(Request $req, $id_sponsor, NormalizerInterface $Normalizer)
{

    $em = $this->getDoctrine()->getManager();
    $sponsor = $em->getRepository(Sponsor::class)->find($id_sponsor);
    $sponsor->setIntitule($req->get('intitule'));
    $sponsor->setDureeContrat($req->get('duree_contrat'));

   

    $em->flush();

    $jsonContent = $Normalizer->normalize($sponsor, 'json', ['groups' => 'spon']);
    return new Response("Sponsor updated successfully " . json_encode($jsonContent));
}
#[Route("/deletejson/{id_sponsor}", name: "deletesponsorJSON")]
public function deleteSponsorJSON(Request $req, $id_sponsor, NormalizerInterface $Normalizer)
{

    $em = $this->getDoctrine()->getManager();
    $sponsor = $em->getRepository(Sponsor::class)->find($id_sponsor);
    $em->remove($sponsor);
    $em->flush();


    $jsonContent = $Normalizer->normalize($sponsor, 'json', ['groups' => 'spon']);
    return new Response("Sponsor deleted successfully " . json_encode($jsonContent));
}
#[Route('/', name: 'app_sponsor_index', methods: ['GET'])]
    public function index(SponsorRepository $sponsorRepository, PaginatorInterface $paginator, Request $request): Response
    {
        $query = $sponsorRepository->createQueryBuilder('s')
            ->orderBy('s.intitule', 'ASC')
            ->getQuery();
        
        $paginatedsponsor = $paginator->paginate(
            $query,
            $request->query->getInt('page', 1),
            4 // Number of results per page
        );
        
        return $this->render('sponsor/index.html.twig', [
            'sponsors' => $paginatedsponsor, // Use the paginated results instead of all results
            'pagination' => $paginatedsponsor,
        ]);
    }

   

    #[Route('/front', name: 'app_sponsor_index1', methods: ['GET'])]
    public function index1(SponsorRepository $sponsorRepository, PaginatorInterface $paginator, Request $request): Response
    {
        $query = $sponsorRepository->createQueryBuilder('s')
        ->orderBy('s.intitule', 'ASC')
        ->getQuery();
    
    $paginatedsponsor = $paginator->paginate(
        $query,
        $request->query->getInt('page', 1),
        4 // Number of results per page
    );
    
    return $this->render('front_sponsor/index.html.twig', [
        'sponsors' => $paginatedsponsor, // Use the paginated results instead of all results
        'pagination' => $paginatedsponsor,
    ]);
    }

    //Exporter pdf (composer require dompdf/dompdf)
     
      #[Route('/pdf', name: 'app_pdf', methods: ['GET'])]
      public function pdf(SponsorRepository $SponsorRepository)
      {
          // Configure Dompdf according to your needs
          $pdfOptions = new Options();
          $pdfOptions->set('defaultFont', 'Arial');
  
          // Instantiate Dompdf with our options
          $dompdf = new Dompdf($pdfOptions);
          // Retrieve the HTML generated in our twig file
          $html = $this->renderView('sponsor/pdf.html.twig', [
              'sponsors' => $SponsorRepository->findAll(),
          ]);
  
          // Load HTML to Dompdf
          $dompdf->loadHtml($html);
          // (Optional) Setup the paper size and orientation 'portrait' or 'portrait'
          $dompdf->setPaper('A4', 'portrait');
  
          // Render the HTML as PDF
          $dompdf->render();
          // Output the generated PDF to Browser (inline view)
          $dompdf->stream("ListeDesSponsors.pdf", [
              "sponsors" => true
          ]);
      }

    #[Route('/new', name: 'app_sponsor_new', methods: ['GET', 'POST'])]
    public function new(Request $request, SponsorRepository $sponsorRepository): Response
    {
        $sponsor = new Sponsor();
        $form = $this->createForm(SponsorType::class, $sponsor);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $sponsorRepository->save($sponsor, true);
            $this->addFlash('success', 'New Sponsor added successfully!');

            $accountSid = 'ACb3512b1baa60eb994049eea0656f6934';
            $authToken = 'db782f1385c2a4a273b50e7c3222a46f';
            $client = new Client($accountSid, $authToken);
    
            $message = $client->messages->create(
                '+21621560477', // replace with admin's phone number
                [
                    'from' => '+15673343461', // replace with your Twilio phone number
                    'body' => 'A new sponsor ' . $form->get('intitule')->getData() .'has been successfully added ' ,
                ]

            );


            return $this->redirectToRoute('app_sponsor_index', [], Response::HTTP_SEE_OTHER);

        }

        return $this->renderForm('sponsor/new.html.twig', [
            'sponsor' => $sponsor,
            'form' => $form,
        ]);
    }

    #[Route('/{id_sponsor}', name: 'app_sponsor_show', methods: ['GET'])]
    public function show(Sponsor $sponsor): Response
    {
        return $this->render('sponsor/show.html.twig', [
            'sponsor' => $sponsor,
        ]);
    }

    #[Route('/{id_sponsor}/edit', name: 'app_sponsor_edit', methods: ['GET', 'POST'])]
    public function edit(Request $request, Sponsor $sponsor, SponsorRepository $sponsorRepository): Response
    {
        $form = $this->createForm(SponsorType::class, $sponsor);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $sponsorRepository->save($sponsor, true);

            return $this->redirectToRoute('app_sponsor_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->renderForm('sponsor/edit.html.twig', [
            'sponsor' => $sponsor,
            'form' => $form,
        ]);
    }

    #[Route('/{id_sponsor}', name: 'app_sponsor_delete', methods: ['POST'])]
    public function delete(Request $request, Sponsor $sponsor, SponsorRepository $sponsorRepository): Response
    {
        if ($this->isCsrfTokenValid('delete'.$sponsor->getId_sponsor(), $request->request->get('_token'))) {
            $sponsorRepository->remove($sponsor, true);
        }

        return $this->redirectToRoute('app_sponsor_index', [], Response::HTTP_SEE_OTHER);
    }

  



#[Route('/search', name: 'app_sponsor_search"')]
public function search(Request $request, NormalizerInterface $Normalizer,  SponsorRepository $sr)
{
    $repository = $this->getDoctrine()->getRepository(Sponsor::class);
    $requestString = $request->get('searchValue');
    $sponsors = $repository->findBySearchQuery($requestString);
    $jsonContent = $Normalizer->normalize($sponsors, 'json', ['groups' => 'sponsor']);
    $retour = json_encode($jsonContent);
    return new Response($retour);
}




}
