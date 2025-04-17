package br.com.steventung.redegram.data.repository

import br.com.steventung.redegram.domain.model.Post
import kotlinx.coroutines.flow.Flow

interface PostRepository {
    fun getPosts(): Flow<List<Post>>
    fun setLikePost(post: Post)
    fun setCommentLike(commentId: Long, postId: Long)
    fun getPostById(postId: Long): Flow<Post?>
}