<?php

namespace App\Controller;

use App\Entity\Evenement;
use App\Form\EvenementType;
use App\Repository\EvenementRepository;
use Doctrine\ORM\EntityManagerInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

use Symfony\Component\Mailer\MailerInterface;
use Symfony\Component\Mime\Email;
use Endroid\QrCode\QrCode;
use Endroid\QrCode\Color\Color;
use Endroid\QrCode\Encoding\Encoding;
use Endroid\QrCode\ErrorCorrectionLevel\ErrorCorrectionLevelLow;
use Endroid\QrCode\Label\Label;
use Endroid\QrCode\Logo\Logo;
use Endroid\QrCode\Writer\PngWriter;
use Endroid\QrCode\Label\Font\NotoSans;
use Symfony\Component\Mailer\Exception\TransportExceptionInterface;
use Knp\Component\Pager\PaginatorInterface;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\Serializer\Normalizer\NormalizerInterface;
use Symfony\Component\Serializer\Serializer;
use Symfony\Component\Serializer\SerializerInterface;

#[Route('/evenement')]
class EvenementController extends AbstractController
{
    

    #[Route("/displayjson1", name: "app_event_displayjson1")]
   
public function displayjson1(EvenementRepository $repo, SerializerInterface $serializer)
{ 
    $event = $repo->findAll();
   
    $json = $serializer->serialize($event, 'json', ['groups' => "event"]);

    return new Response($json);
}

#[Route("/event/{id_event}", name: "one_evenement")]
public function evenementId($id_event, NormalizerInterface $normalizer, EvenementRepository $repo)
{
    $evenement = $repo->find($id_event);
    $evenementNormalises = $normalizer->normalize($evenement, 'json', ['groups' => "event"]);
    return new Response(json_encode($evenementNormalises));
}

#[Route("/newjson", name: "addeventJSON")]
public function addDestinationJSON(Request $req,   NormalizerInterface $Normalizer, MailerInterface $mailer)
{

    $em = $this->getDoctrine()->getManager();
    $evenement = new evenement();
    $evenement->setDuree($req->get('duree'));
    $evenement->setPrix($req->get('prix'));

       
        
        $dateDebcStr = $req->get('date_deb');
        
        $dateDeb = \DateTime::createFromFormat('Y-m-d', $dateDebcStr);
        $evenement->setDateDeb($dateDeb);
        
      
        $dateFincStr=$req->get('date_fin');
        $dateFin = \DateTime::createFromFormat('Y-m-d', $dateFincStr);
        $evenement->setDateFin($dateFin);
    $evenement->setNomEvent($req->get('nom_event'));
    $em->persist($evenement);
    $em->flush();

    $jsonContent = $Normalizer->normalize($evenement, 'json', ['groups' => 'event']);
    $email = (new Email())
    ->from('farah.battikh@esprit.tn')
    ->To('eya.mosbeh@esprit.tn')
    ->subject(' un evenement a ete ajoute')
            ->text("<p> Bonjour</p> plusieurs nouvelles are coming ");
 
   
try {
    $mailer->send($email);
} catch (TransportExceptionInterface $e) {
    // Gérer les erreurs d'envoi de courriel
}

    
    return new Response(json_encode($jsonContent));
}

#[Route("/updatejson/{id_event}", name: "updateeventJSON")]
public function updateDestinationJSON(Request $req, $id_event, NormalizerInterface $Normalizer)
{

    $em = $this->getDoctrine()->getManager();
    $evenement = $em->getRepository(Evenement::class)->find($id_event);
    $evenement->setDuree($req->get('duree'));
    $evenement->setPrix($req->get('prix'));

       
        
        $dateDebcStr = $req->get('date_deb');
        
        $dateDeb = \DateTime::createFromFormat('Y-m-d', $dateDebcStr);
        $evenement->setDateDeb($dateDeb);
        
      
        $dateFincStr=$req->get('date_fin');
        $dateFin = \DateTime::createFromFormat('Y-m-d', $dateFincStr);
        $evenement->setDateFin($dateFin);
    $evenement->setNomEvent($req->get('nom_event'));

    $em->flush();

    $jsonContent = $Normalizer->normalize($evenement, 'json', ['groups' => 'event']);
    return new Response("Student updated successfully " . json_encode($jsonContent));
}

#[Route("/deletejson/{id_event}", name: "deleteeventJSON")]
public function deleteStudentJSON(Request $req, $id_event, NormalizerInterface $Normalizer)
{

    $em = $this->getDoctrine()->getManager();
    $evenement = $em->getRepository(Evenement::class)->find($id_event);
    $em->remove($evenement);
    $em->flush();
    $jsonContent = $Normalizer->normalize($evenement, 'json', ['groups' => 'event']);
    return new Response("Student deleted successfully " . json_encode($jsonContent));
}

  #[Route('/', name: 'app_evenement_index', methods: ['GET'])]
public function index(Request $request, EvenementRepository $evenementRepository, PaginatorInterface $paginator): Response
{
    $sort = $request->query->get('sort');
    $query = $evenementRepository->findAllSortedByName($sort);
    $paginatedEvents = $paginator->paginate(
        $query,
        $request->query->getInt('page', 1),
        4
    );
    
    return $this->render('evenement/index.html.twig', [
        'evenements' => $paginatedEvents,
        'sort' => $sort,
    ]);
}

    #[Route('/front', name: 'app_evenement_index2', methods: ['GET'])]
    public function index2(Request $request, EvenementRepository $evenementRepository): Response
    {
        $sort = $request->query->get('sort');
        $evenements = $evenementRepository->findAllSortedByName($sort);
        
        return $this->render('front_event/index2.html.twig', [
            'evenements' => $evenements,
            'sort' => $sort,
        ]);
    }
    #[Route('/new', name: 'app_evenement_new', methods: ['GET', 'POST'])]
    public function new(Request $request, EvenementRepository $evenementRepository, MailerInterface $mailer): Response
    {
        $evenement = new Evenement();
        $form = $this->createForm(EvenementType::class, $evenement);
        $form->handleRequest($request);
    
        if ($form->isSubmitted() && $form->isValid()) {
            
            $evenementRepository->save($evenement, true);
            $this->addFlash('success', 'New event added successfully!');

            $email = (new Email())
        ->from('farah.battikh@esprit.tn')
        ->To('eya.mosbeh@esprit.tn')
        ->subject(' un evenement a ete ajoute')
                ->text("<p> Bonjour</p> plusieurs nouvelles are coming ");
     
       
 try {
        $mailer->send($email);
    } catch (TransportExceptionInterface $e) {
        // Gérer les erreurs d'envoi de courriel
    }
    
            return $this->redirectToRoute('app_evenement_index', [], Response::HTTP_SEE_OTHER);
        }
    
        return $this->renderForm('evenement/new.html.twig', [
            'evenement' => $evenement,
            'form' => $form,
        ]);
    }

    #[Route('/{id_event}', name: 'app_evenement_show', methods: ['GET'])]
    public function show(Evenement $evenement): Response
    {
        $qrImageData = $this->qrcoding('Nom Evenement :'.$evenement->getNomEvent()."*
        *"."duree :".$evenement->getDuree()."*   
       **"."prix :".$evenement->getPrix()
    
    
    
    );
        return $this->render('evenement/show.html.twig', [
            'evenement' => $evenement,
            'qr_code'=>$qrImageData,
        ]);
    }
    #[Route('/front/{id_event}', name: 'app_evenement_show2', methods: ['GET'])]
    public function show2(Evenement $evenement): Response
    {
        $qrImageData = $this->qrcoding('Nom Evenement :'.$evenement->getNomEvent()."*
        *"."duree :".$evenement->getDuree()."*   
       **"."prix :".$evenement->getPrix()
    
    
    
    );
        return $this->render('front_event/show.html.twig', [
            'evenement' => $evenement,
            'qr_code'=>$qrImageData,
        ]);
    }

    #[Route('/{id_event}/edit', name: 'app_evenement_edit', methods: ['GET', 'POST'])]
    public function edit(Request $request, Evenement $evenement, EvenementRepository $evenementRepository): Response
    {
        $form = $this->createForm(EvenementType::class, $evenement);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $evenementRepository->save($evenement, true);

            return $this->redirectToRoute('app_evenement_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->renderForm('evenement/edit.html.twig', [
            'evenement' => $evenement,
            'form' => $form,
        ]);
    }

    #[Route('/{id_event}', name: 'app_evenement_delete', methods: ['POST'])]
    public function delete(Request $request, Evenement $evenement, EvenementRepository $evenementRepository): Response
    {
        if ($this->isCsrfTokenValid('delete'.$evenement->getId(), $request->request->get('_token'))) {
            $evenementRepository->remove($evenement, true);
        }

        return $this->redirectToRoute('app_evenement_index', [], Response::HTTP_SEE_OTHER);
    }
    
   
    #[Route('/showmap/{id_event}', name: 'app_evenement_map', methods: ['GET'])]
    public function map(Evenement $e, EntityManagerInterface $entityManager): Response
    {

        $evenements = $entityManager
            ->getRepository(Evenement::class)->findBy(
                ['id_event' => $e]
            );
        return $this->render('evenement/api_arcgis.html.twig', [
            'evenements' => $evenements,
        ]);
    }
   
    public function changeLocale(Request $request, $_locale)
    {
        $request->getSession()->set('_locale', $_locale);
        
        $referer = $request->headers->get('referer');
        return $this->redirect($referer);
    }

    public function qrcoding($data)
   {$writer = new PngWriter();
    $qrCode = QrCode::create($data)
        ->setEncoding(new Encoding('UTF-8'))
        ->setErrorCorrectionLevel(new ErrorCorrectionLevelLow())
        ->setSize(200)
        ->setMargin(0)
        ->setForegroundColor(new Color(0, 0, 0))
        ->setBackgroundColor(new Color(255, 255, 255));
    $logo = Logo::create('front-office\img\vendor-3.jpg')
        ->setResizeToWidth(90);
    $label = Label::create('')->setFont(new NotoSans(8));

    $qrCodes = [];
    $qrCodes['img'] = $writer->write($qrCode, $logo)->getDataUri();
    $qrCodes['simple'] = $writer->write(
                            $qrCode,
                            null,
                            $label->setText('Simple')
                        )->getDataUri();

    $qrCode->setForegroundColor(new Color(255, 0, 0));
    $qrCodes['changeColor'] = $writer->write(
        $qrCode,
        null,
        $label->setText('Color Change')
    )->getDataUri();

    $qrCode->setForegroundColor(new Color(0, 0, 0))->setBackgroundColor(new Color(255, 255, 255));
    $qrCodes['changeBgColor'] = $writer->write(
        $qrCode,
        null,
        $label->setText('Background Color Change')
    )->getDataUri();

    $qrCode->setSize(200)->setForegroundColor(new Color(0, 0, 0))->setBackgroundColor(new Color(255, 255, 255));
    $qrCodes['withImage'] = $writer->write(
        $qrCode,
        $logo,
        $label->setText('Reused')->setFont(new NotoSans(20))
    )->getDataUri();
    return $qrCodes;
}


}
