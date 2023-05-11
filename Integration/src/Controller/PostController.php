<?php

namespace App\Controller;

use App\Entity\Post;
use App\Form\PostType;
use App\Repository\PostRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Doctrine\Persistence\ManagerRegistry;
use App\Entity\Message;
use Knp\Component\Pager\PaginatorInterface;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\Serializer\Normalizer\NormalizerInterface;
use Symfony\Component\Serializer\Serializer;
use Symfony\Component\Serializer\SerializerInterface;


#[Route('/post')]
class PostController extends AbstractController
{
    #[Route("/displayPostjson", name: "app_post_displaypost")]
    public function displayjson1(PostRepository $repo, SerializerInterface $serializer)
    {
        $post = $repo->findAll();
        $json = $serializer->serialize($post, 'json', ['groups' => "posts"]);
        return new Response($json);
    }

    #[Route("/post/{id}", name: "one_post")]
    public function PostId($id, NormalizerInterface $normalizer, PostRepository $repo)
    {
        $post = $repo->find($id);
        $postNormalises = $normalizer->normalize($post, 'json', ['groups' => "posts"]);
        return new Response(json_encode($postNormalises));
    }

    #[Route("/newPostjson", name: "addpostJSON")]
    public function addpostJSON(Request $req,   NormalizerInterface $Normalizer)
    {

        $em = $this->doctrine->getManager();
        $post = new post();
        $post->setSujet($req->get('sujet'));

        //$evenement->setDateDeb(new \DateTime('now'));
        $em->persist($post);
        $em->flush();

        $jsonContent = $Normalizer->normalize($post, 'json', ['groups' => 'posts']);
        return new Response(json_encode($jsonContent));
    }

    #[Route("/updatePostjson", name: "updatepostJSON")]
    public function updatepostJSON(Request $request, NormalizerInterface $normalizer)
    {
        $id = $request->query->get('id_post');
        $em = $this->doctrine->getManager();
        $post = $em->getRepository(post::class)->find($id);
        if (!$post) {
            return new JsonResponse([
                'error' => sprintf('Post with ID %s not found', $id),
            ], 404);
        }
        $post->setSujet($request->get('sujet'));
        $em->flush();
        $jsonContent = $normalizer->normalize($post, 'json', ['groups' => 'posts']);
        return new JsonResponse(['message' => 'Post updated successfully', 'data' => $jsonContent]);
    }

    #[Route("/deletePostjson", name: "deletepostJSON")]
    public function deleteStudentJSON(Request $request, NormalizerInterface $normalizer)
    {
        $id = $request->query->get('id_post');
        $em = $this->doctrine->getManager();
        $post = $em->getRepository(Post::class)->find($id);
        if (!$post) {
            return new JsonResponse([
                'error' => sprintf('Post with ID %s not found', $id),
            ], 404);
        }
        $em->remove($post);
        $em->flush();
        $jsonContent = $normalizer->normalize($post, 'json', ['groups' => 'posts']);
        return new JsonResponse(['message' => 'Post deleted successfully', 'data' => $jsonContent]);
    }


    #[Route('/', name: 'app_post_index', methods: ['GET'])]
    public function index(PostRepository $postRepository, PaginatorInterface $paginator, Request $request): Response
    {
        $post = $postRepository->findAll();
        $post = $paginator->paginate(
            $post, /* query NOT result */
            $request->query->getInt('page', 1), /*page number*/
            3
        );
        return $this->render('post/index.html.twig', [
            'posts' => $post
        ]);
    }

    #[Route('/signaled', name: 'app_post_signaled_index', methods: ['GET'])]
    public function Signaledindex(PostRepository $postRepository, PaginatorInterface $paginator, Request $request): Response
    {
        $post = $postRepository->findAll();
        $post = $paginator->paginate(
            $post, /* query NOT result */
            $request->query->getInt('page', 1), /*page number*/
            3
        );
        return $this->render('post/signaled.html.twig', [
            'posts' => $post
        ]);
    }

    public function __construct(private ManagerRegistry $doctrine)
    {
    }

    #[Route('/new', name: 'app_post_new', methods: ['GET', 'POST'])]
    public function new(Request $request, PostRepository $postRepository): Response
    {
        $post = new Post();
        $form = $this->createForm(PostType::class, $post);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $postRepository->save($post, true);

            return $this->redirectToRoute('app_post_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->renderForm('post/new.html.twig', [
            'post' => $post,
            'form' => $form,
        ]);
    }


    #[Route('/{id_post}', name: 'app_post_show', methods: ['GET'])]
    public function show(Post $post): Response
    {
        // Fetch the associated messages for the Post entity
        $messages = $this->doctrine->getRepository(Message::class)->findBy(['id_post' => $post]);

        // calculate the average note
        $notes = array_map(function ($message) {
            return $message->getNote();
        }, $messages);

        $averageNote = count($notes) > 0 ? array_sum($notes) / count($notes) : 0;
        $post->incrementView();

        $entityManager = $this->doctrine->getManager();
        $entityManager->persist($post);
        $entityManager->flush();
        // Render a template to display the Post entity and its associated messages
        return $this->render('post/show.html.twig', [
            'post' => $post,
            'messages' => $messages,
            'averageNote' => $averageNote
        ]);
    }

    #[Route('/message/{id}/like', name: 'app_message_like', methods: ['POST'])]
    public function like(Message $message): Response
    {
        $message->incrementLikes();

        $entityManager = $this->doctrine->getManager();
        $entityManager->persist($message);
        $entityManager->flush();

        return $this->redirectToRoute('app_post_show', [
            'id_post' => $message->getIdPost()->getId_post(),
        ]);
    }

    #[Route('/message/{id}/dislike', name: 'app_message_dislike', methods: ['POST'])]
    public function dislike(Message $message): Response
    {
        $message->decrementLikes();

        $entityManager = $this->doctrine->getManager();
        $entityManager->persist($message);
        $entityManager->flush();

        return $this->redirectToRoute('app_post_show', [
            'id_post' => $message->getIdPost()->getId_post(),
        ]);
    }

    #[Route('/{id_post}/edit', name: 'app_post_edit', methods: ['GET', 'POST'])]
    public function edit(Request $request, Post $post, PostRepository $postRepository): Response
    {
        $form = $this->createForm(PostType::class, $post);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $postRepository->save($post, true);

            return $this->redirectToRoute('app_post_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->renderForm('post/edit.html.twig', [
            'post' => $post,
            'form' => $form,
        ]);
    }

    #[Route('/{id_post}', name: 'app_post_delete', methods: ['POST'])]
    public function delete(Request $request, Post $post, PostRepository $postRepository): Response
    {
        if ($this->isCsrfTokenValid('delete' . $post->getId_post(), $request->request->get('_token'))) {
            $postRepository->remove($post, true);
        }

        return $this->redirectToRoute('app_post_index', [], Response::HTTP_SEE_OTHER);
    }

    #[Route('/signal/{id_post}', name: 'app_post_signal', methods: ['GET'])]
    public function signal(Post $post)
    {
        $em = $this->doctrine->getManager();
        $post->setSignaled(1);
        $em->flush();
        return $this->redirectToRoute('app_post_index');
    }
    #[Route('/unsignal/{id_post}', name: 'app_post_unsignal', methods: ['GET'])]
    public function unsignal(Post $post)
    {
        $em = $this->doctrine->getManager();
        $post->setSignaled(0);
        $em->flush();
        return $this->redirectToRoute('app_post_signaled_index');
    }
}
