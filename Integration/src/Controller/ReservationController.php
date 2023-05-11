<?php

namespace App\Controller;

use App\Entity\Reservation;
use App\Form\ReservationType;
use App\Repository\ReservationRepository;
use App\Repository\EvenementRepository;



use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Doctrine\ORM\EntityManagerInterface;
use TelegramBot\Api\BotApi;
use Symfony\Contracts\HttpClient\HttpClientInterface;
use Knp\Component\Pager\PaginatorInterface;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\Serializer\Normalizer\NormalizerInterface;
use Symfony\Component\Serializer\Serializer;
use Symfony\Component\Serializer\SerializerInterface;



#[Route('/reservation')]
class ReservationController extends AbstractController
{
    #[Route("/allReservations", name: "app_Reservations")]
    public function displayjson1(ReservationRepository $repo, SerializerInterface $serializer)
    {
        $Reservations = $repo->findAll();
       
        $json = $serializer->serialize($Reservations, 'json', ['groups' => "spon"]);
    
        return new Response($json);
    }
    #[Route("/addReservation", name: "addReservationJSON")]
    public function addReservationJSON(Request $req, EntityManagerInterface $em, NormalizerInterface $Normalizer, EvenementRepository $ev)
    {
        
    
        $Reservation = new Reservation();
        
        $Reservation->setIdUser($req->get('id_user'));
        $Reservation->setEvent($ev->find($req->get('id_event')));
        $Reservation->setQte($req->get('qte'));
       
      
    
        $em->persist($Reservation);
        $em->flush();
    
        $jsonContent = $Normalizer->normalize($Reservation, 'json', ['groups' => 'spon']);
        return new Response(json_encode($jsonContent));
    }
  
  private $entityManager;

    public function __construct(EntityManagerInterface $entityManager)
    {
        $this->entityManager = $entityManager;
    }
    
    #[Route("/updateReservation/{id_Reservation}", name: "updateReservationJSON")]
    public function updateReservationJSON(Request $req, EntityManagerInterface $em, NormalizerInterface $Normalizer, $id_Reservation, EvenementRepository $ev)
    {
        
        $Reservation = $em->getRepository(Reservation::class)->find($id_Reservation);
        $Reservation->setIdUser($req->get('id_user'));
        $Reservation->setEvent($ev->find($req->get('id_event')));
        $Reservation->setQte($req->get('qte'));

        $em->flush();

        $jsonContent = $Normalizer->normalize($Reservation, 'json', ['groups' => 'spon']);
        return new Response("Reservation updated successfully " . json_encode($jsonContent));
    }


#[Route("/deletejson/{id_Reservation}", name: "deleteReservationJSON")]
public function deleteReservationJSON(Request $req, $id_Reservation, NormalizerInterface $Normalizer)
{

    
    $Reservation = $em->getRepository(Reservation::class)->find($id_Reservation);
    $em->remove($Reservation);
    $em->flush();
    $jsonContent = $Normalizer->normalize($Reservation, 'json', ['groups' => 'spon']);
    return new Response("Reservation deleted successfully " . json_encode($jsonContent));
}
    

    
    #[Route('/', name: 'reservation_index', methods: ['GET'])]
public function index(Request $request, ReservationRepository $ReservationRepository,PaginatorInterface $paginator): Response
{
    $query = $ReservationRepository->createQueryBuilder('s')
        ->orderBy('s.id', 'ASC')
        ->getQuery();
        $paginatedreservation = $paginator->paginate(
            $query,
            $request->query->getInt('page', 1),2 // Number of results per page
        );
    $tri = $request->query->get('tri') ?? 'id'; // Default sort by ID
    $direction = $request->query->get('dir') ?? 'asc'; // Default sort direction
    
    $reservations = $ReservationRepository->findBy([], [$tri => $direction]);

    return $this->render('reservation/index.html.twig', [
        
        'tri' => $tri,
        'dir' => $direction,
        'reservations' => $paginatedreservation,
        'reser'=>$reservations,
            'pagination' => $paginatedreservation,
    ]);
}
#[Route('/front', name: 'app_reservation_index1', methods: ['GET'])]
    public function index1(Request $request, ReservationRepository $ReservationRepository,PaginatorInterface $paginator): Response
    {
        $query = $ReservationRepository->createQueryBuilder('s')
        ->orderBy('s.id', 'ASC')
        ->getQuery();
        $paginatedreservation = $paginator->paginate(
            $query,
            $request->query->getInt('page', 1),2 // Number of results per page
        );
    $tri = $request->query->get('tri') ?? 'id'; // Default sort by ID
    $direction = $request->query->get('dir') ?? 'asc'; // Default sort direction
    
    $reservations = $ReservationRepository->findBy([], [$tri => $direction]);
        return $this->render('reservation_front/index.html.twig', [
            'tri' => $tri,
        'dir' => $direction,
        'reservations' => $paginatedreservation,
        'reser'=>$reservations,
            'pagination' => $paginatedreservation,
        ]);
    }



    #[Route('/new', name: 'reservation_new', methods: ['GET', 'POST'])]
    public function new(Request $request): Response
    {
        $reservation = new Reservation();
        $form = $this->createForm(ReservationType::class, $reservation);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $this->entityManager->persist($reservation);
            $this->entityManager->flush();
            //send telegram message
            $botToken = '6128512482:AAF8Lg8cnnhJRZ_0BMNyV4dK7TZW1TTT_P8';
            $botApi = new BotApi($botToken);
    
            $chatId = '5461160264';
            $message = 'Reservation is done with success! (symfony)';
    
            $botApi->sendMessage($chatId, $message);
    
            //return new Response('Message sent!');

            return $this->redirectToRoute('reservation_index');
        }

        return $this->render('reservation/new.html.twig', [
            'reservation' => $reservation,
            'form' => $form->createView(),
        ]);
    }

    #[Route('/{id}', name: 'reservation_show', methods: ['GET'])]
    public function show(Reservation $reservation): Response
    {
        return $this->render('reservation/show.html.twig', [
            'reservation' => $reservation,
        ]);
    }

    #[Route('/{id}/edit', name: 'reservation_edit', methods: ['GET', 'POST'])]
    public function edit(Request $request, Reservation $reservation): Response
    {
        $form = $this->createForm(ReservationType::class, $reservation);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $this->entityManager->flush();

            return $this->redirectToRoute('reservation_index');
        }

        return $this->render('reservation/edit.html.twig', [
            'reservation' => $reservation,
            'form' => $form->createView(),
        ]);
    }

    #[Route('/{id}', name: 'reservation_delete', methods: ['POST'])]
    public function delete(Request $request, Reservation $reservation): Response
    {
        if ($this->isCsrfTokenValid('delete'.$reservation->getId(), $request->request->get('_token'))) {
            $this->entityManager->remove($reservation);
            $this->entityManager->flush();
        }

        return $this->redirectToRoute('reservation_index');
    }
   

  
        
    #[Route('/weather', name: 'reservation_weather', methods: ['GET'])]
    public function weather(Request $request, HttpClientInterface $httpClient): Response
    {
        $openWeatherId = $request->query->get('id');

        if (!$openWeatherId) {
            throw new \InvalidArgumentException('OpenWeather ID parameter is required.');
        }

        $response = $httpClient->request('GET', 'https://api.openweathermap.org/data/2.5/weather', [
            'query' => [
                'id' => $openWeatherId,
                'appid' => '6f467fcce969a6ac1268731d478e7152', // Replace with your OpenWeather API key
            ],
        ]);

        $weather = json_decode($response->getContent());

        return $this->render('reservation/weather.html.twig', [
            'weather' => $weather,
        ]);
    }
    
    }




    




