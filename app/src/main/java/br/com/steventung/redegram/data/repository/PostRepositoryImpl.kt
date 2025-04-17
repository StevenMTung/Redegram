package br.com.steventung.redegram.data.repository

import br.com.steventung.redegram.data.samples.Samples
import br.com.steventung.redegram.domain.model.Post
import kotlinx.coroutines.flow.Flow

class PostRepositoryImpl(
    private val samples: Samples
) : PostRepository {
    override fun getPosts(): Flow<List<Post>> {
        return samples.getPosts()
    }

    override fun setLikePost(post: Post) {
        samples.setLikePost(post)
    }

    override fun setCommentLike(commentId: String, postId: Long) {
        samples.setLikeComment(commentId, postId)
    }

    override fun getPostById(postId: Long): Flow<Post?> {
        return samples.getPostById(postId)
    }
}