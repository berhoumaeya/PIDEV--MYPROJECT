<?php

namespace App\Controller;

use App\Entity\Destination;
use App\Form\DestinationType;
use App\Repository\DestinationRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\HttpFoundation\ResponseHeaderBag;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\HttpFoundation\Session\Flash\FlashBagInterface;
use PhpOffice\PhpSpreadsheet\IOFactory;
use PhpOffice\PhpSpreadsheet\Spreadsheet;
use PhpOffice\PhpSpreadsheet\Writer\Xlsx;
use Endroid\QrCode\Builder\Builder;
use Endroid\QrCode\Encoding\Encoding;
use Endroid\QrCode\ErrorCorrectionLevel\ErrorCorrectionLevelHigh;
use Endroid\QrCode\Label\Alignment\LabelAlignmentCenter;
use Endroid\QrCode\Label\Margin\Margin;
use Endroid\QrCode\Writer\PngWriter;
use Symfony\Component\HttpFoundation\BinaryFileResponse;
use Endroid\QrCode\Label\Font\NotoSans;
use Endroid\QrCode\RoundBlockSizeMode\RoundBlockSizeModeMargin;
use Endroid\QrCode\Builder\BuilderInterface; // Add this "use" statement
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\Serializer\Normalizer\NormalizerInterface;
use Symfony\Component\Serializer\Serializer;
use Symfony\Component\Serializer\SerializerInterface;
use Twilio\Rest\Client;
use PhpOffice\PhpSpreadsheet\Style\Font;










#[Route('/destination')]
class DestinationController extends AbstractController
{   #[Route('/qrjson/{id}', name: 'app_destination_qrjson', methods: ['GET'])]

public function qr($id, NormalizerInterface $normalizer)
{
    // Get the row's data from the table based on the $id
    $destination = $this->getDoctrine()->getRepository(Destination::class)->find($id); // Update with your actual entity name and repository method

    if (!$destination) {
        throw $this->createNotFoundException('Destination not found');
    }

    // Generate QR code with the row's data
    $result = Builder::create() // Update to use BuilderInterface instead of Builder
        ->writer(new PngWriter())
        ->data("Destination: " . $destination->getPays() . ' - ' . $destination->getVille()  )
        ->encoding(new Encoding('UTF-8'))
        ->errorCorrectionLevel(new ErrorCorrectionLevelHigh())
        ->size(300)
        ->margin(10)
        ->labelText("")
        ->labelAlignment(new LabelAlignmentCenter())
        ->labelMargin(new Margin(15, 5, 5, 5))
        ->build();

    $namePng = uniqid('', '') . '.png';
    $result->saveToFile($this->getParameter('kernel.project_dir') . '/public/back-office/assets/img/qrcode' . $namePng); // Update with the actual path to your QR code folder

    // Set the response headers to download the file
    $response = new Response();
    $response->headers->set('Content-Disposition', $response->headers->makeDisposition(
        ResponseHeaderBag::DISPOSITION_ATTACHMENT,
        $namePng
    ));

    // Return the QR code file as the response content
    $response->setContent(file_get_contents($this->getParameter('kernel.project_dir') . '/public/back-office/assets/img/qrcode' . $namePng));
    $response->headers->set('Content-Type', 'image/png');

    return $response;
}





    #[Route('/exportjson', name: 'app_destination_exportjson', methods: ['GET'])]
    public function exportToExceljson(DestinationRepository $destinationRepository, NormalizerInterface $normalizer): BinaryFileResponse
    {
        $spreadsheet = new Spreadsheet();
        $sheet = $spreadsheet->getActiveSheet();

        // Set the headers
       // $sheet->setCellValue('A1', 'ID');
        $sheet->setCellValue('A1', 'Ville');
        $sheet->setCellValue('B1', 'Pays');

        // Get the destinations from the repository
        $destinations = $destinationRepository->findAll();

        // Loop through the destinations and add them to the sheet
        $row = 2;
        foreach ($destinations as $destination) {
         //   $sheet->setCellValue('A' . $row, $destination->getId());
            $sheet->setCellValue('A' . $row, $destination->getVille());
            $sheet->setCellValue('B' . $row, $destination->getPays());
            $row++;
        }

        // Create the Excel file
        $writer = new Xlsx($spreadsheet);
        $fileName = 'destinations.xlsx';
        $writer->save($fileName);

        // Get the content of the Excel file as JSON
        $fileContents = file_get_contents($fileName);
        $jsonContent = $normalizer->normalize($fileContents, 'json');

        // Return the JSON response with the Excel file as attachment
        return new BinaryFileResponse($fileName, 200, [
            'Content-Type' => 'application/json',
            'Content-Disposition' => 'attachment; filename="' . $fileName . '"',
        ], true);
    }


    #[Route("/displaydestinationjson", name: "app_destination_displayjson1")]
   
    public function displayjson1(DestinationRepository $repo, SerializerInterface $serializer)
    {
        $destinations = $repo->findAll();
       
        $json = $serializer->serialize($destinations, 'json', ['groups' => "destinations"]);

        return new Response($json);
    }

    #[Route("/dest/{id}", name: "one_destination")]
    public function DestinationId($id, NormalizerInterface $normalizer, DestinationRepository $repo)
    {
        $destinations = $repo->find($id);
        $destinationsNormalises = $normalizer->normalize($destinations, 'json', ['groups' => "destinations"]);
        return new Response(json_encode($destinationsNormalises));
    }

    #[Route("/newdestjson", name: "adddestinationJSON")]
    public function addDestinationJSON(Request $req,   NormalizerInterface $Normalizer)
    {

        $em = $this->getDoctrine()->getManager();
        $destination = new Destination();
        $destination->setVille($req->get('ville'));
        $destination->setpays($req->get('pays'));
        $em->persist($destination);
        $em->flush();


        $jsonContent = $Normalizer->normalize($destination, 'json', ['groups' => 'destinations']);
        return new Response(json_encode($jsonContent));
    }

    #[Route("/updatedestjson/{id}", name: "updatedestinationJSON")]
    public function updateDestinationJSON(Request $req, $id, NormalizerInterface $Normalizer)
    {

        $em = $this->getDoctrine()->getManager();
        $destination = $em->getRepository(Destination::class)->find($id);
        $destination->setVille($req->get('ville'));
        $destination->setPays($req->get('pays'));

        $em->flush();

        $jsonContent = $Normalizer->normalize($destination, 'json', ['groups' => 'destinations']);
        return new Response("Student updated successfully " . json_encode($jsonContent));
    }

    #[Route("/deletedestjson/{id}", name: "deletedestinationJSON")]
    public function deleteStudentJSON(Request $req, $id, NormalizerInterface $Normalizer)
    {

        $em = $this->getDoctrine()->getManager();
        $destination = $em->getRepository(Destination::class)->find($id);
        $em->remove($destination);
        $em->flush();
        $jsonContent = $Normalizer->normalize($destination, 'json', ['groups' => 'destinations']);
        return new Response("Student deleted successfully " . json_encode($jsonContent));
    }
    
    #[Route('/', name: 'app_destination_index', methods: ['GET'])]
    public function index(DestinationRepository $destinationRepository): Response
    {
        return $this->render('destination/index.html.twig', [
            'destinations' => $destinationRepository->findAll(),
        ]);
    }

    

    #[Route('/new', name: 'app_destination_new', methods: ['GET', 'POST'])]
    public function new(Request $request, DestinationRepository $destinationRepository): Response
    {
        $destination = new Destination();
        $form = $this->createForm(DestinationType::class, $destination);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $destinationRepository->save($destination, true);
            $this->addFlash('success', 'New destination added successfully!');

            return $this->redirectToRoute('app_destination_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->renderForm('destination/new.html.twig', [
            'destination' => $destination,
            'form' => $form,
        ]);
    }

    #[Route('/{id}', name: 'app_destination_show', methods: ['GET'])]
    public function show(Destination $destination): Response
    {
        return $this->render('destination/show.html.twig', [
            'destination' => $destination,
        ]);
    }

    #[Route('/{id}/edit', name: 'app_destination_edit', methods: ['GET', 'POST'])]
public function edit(Request $request, Destination $destination, DestinationRepository $destinationRepository): Response
{
    $originalData = clone $destination; // Create a clone of the original entity data

    $form = $this->createForm(DestinationType::class, $destination);
    $form->handleRequest($request);

    if ($form->isSubmitted() && $form->isValid()) {
        $destinationRepository->save($destination, true);

        // Check if any changes were made to the form
        if ($destination != $originalData) {
            $this->addFlash('success', 'Destination modified successfully!');
            
        } else {
            $this->addFlash('error', 'No changes made to destination.');
            
        }

       return $this->redirectToRoute('app_destination_index', [], Response::HTTP_SEE_OTHER);
    }

    return $this->renderForm('destination/edit.html.twig', [
        'destination' => $destination,
        'form' => $form,
    ]);
}


    #[Route('/{id}', name: 'app_destination_delete', methods: ['POST'])]
    public function delete(Request $request, Destination $destination, DestinationRepository $destinationRepository): Response
    {
        if ($this->isCsrfTokenValid('delete'.$destination->getId(), $request->request->get('_token'))) {
            $destinationRepository->remove($destination, true);
            $this->addFlash('success', 'Destination deleted successfully!');
        }

        return $this->redirectToRoute('app_destination_index', [], Response::HTTP_SEE_OTHER);
    }

    public function exportToExcel()
{
    // Fetch the destinations from your database or wherever you store them
    $destinations = $this->getDoctrine()->getRepository(Destination::class)->findAll();

    // Create a new Spreadsheet object
    $spreadsheet = new Spreadsheet();
    $sheet = $spreadsheet->getActiveSheet();
    $sheet->setCellValue('A1', 'Pays');
    $sheet->setCellValue('B1', 'Ville');

    

    // Loop through the destinations and write the pays and ville properties to the spreadsheet
    $row = 2;
    foreach ($destinations as $destination) {
        $sheet->setCellValue('A' . $row, $destination->getPays());
        $sheet->setCellValue('B' . $row, $destination->getVille());
        $row++;
    }

    // Create a new Xlsx writer and save the spreadsheet to a file
    $writer = new Xlsx($spreadsheet);
    $filename = 'destinations.xlsx';
    $writer->save($filename);

    // Send the Excel file as a response
    $response = new Response(file_get_contents($filename));
    $disposition = $response->headers->makeDisposition(
        ResponseHeaderBag::DISPOSITION_ATTACHMENT,
        $filename
    );
    $response->headers->set('Content-Disposition', $disposition);
    unlink($filename); // Delete the temporary file
    return $response;
}


public function generateQrCode($id)
{
    // Get the row's data from the table based on the $id
    $destination = $this->getDoctrine()->getRepository(Destination::class)->find($id); // Update with your actual entity name and repository method

    if (!$destination) {
        throw $this->createNotFoundException('Destination not found');
    }

    // Generate QR code with the row's data
    $result = Builder::create() // Update to use BuilderInterface instead of Builder
        ->writer(new PngWriter())
        ->data("Destination: " . $destination->getPays() . ' - ' . $destination->getVille()  )
        ->encoding(new Encoding('UTF-8'))
        ->errorCorrectionLevel(new ErrorCorrectionLevelHigh())
        ->size(300)
        ->margin(10)
        ->labelText("")
        ->labelAlignment(new LabelAlignmentCenter())
        ->labelMargin(new Margin(15, 5, 5, 5))
        ->build();

    $namePng = uniqid('', '') . '.png';
    $result->saveToFile($this->getParameter('kernel.project_dir') . '/public/back-office/assets/img/qrcode' . $namePng); // Update with the actual path to your QR code folder

    // Return a response with the QR code image as a download
    $response = new Response();
    $response->headers->set('Content-Type', 'image/png');
    $response->headers->set('Content-Disposition', 'attachment; filename="' . $namePng . '"');
    $response->setContent(file_get_contents($this->getParameter('kernel.project_dir') . '/public/back-office/assets/img/qrcode' . $namePng));
    return $response;
}









}
