package br.com.steventung.redegram.data.samples

import android.util.Log
import br.com.steventung.redegram.R
import br.com.steventung.redegram.domain.model.Comment
import br.com.steventung.redegram.domain.model.Post
import br.com.steventung.redegram.domain.model.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class Samples {
    companion object {
        private var _postsList = mutableListOf(
            Post(
                postId = 1,
                authorPostId = "robson@email.com",
                authorPostImage = R.drawable.perfil_brasileiro,
                authorPostName = "robson_silva",
                postImage = R.drawable.barbecue,
                postDescription = "Sexta-feira! Dia de churrasco e cerveja, vou comer e beber até anoitecer!",
                postTotalLikes = 90,
                comments = listOf(
                    Comment(
                        authorId = "michael@email.com",
                        authorImage = R.drawable.perfil_alemao,
                        authorName = "michael-muller",
                        content = "Es gibt nichts Besseres als ein gutes Barbecue! Viel Spaß mein Freund \uD83D\uDE0D\uD83D\uDD25",
                        commentTotalLikes = 15,
                        isCommentLiked = true
                    ),
                    Comment(
                        authorId = "park@email.com",
                        authorImage = R.drawable.perfil_coreana,
                        authorName = "parkYuna",
                        content = "저는 바비큐를 정말 좋아해요!",
                        commentTotalLikes = 1,
                        isCommentLiked = true
                    ),
                    Comment(
                        authorId = "john@email.com",
                        authorImage = R.drawable.perfil_americano,
                        authorName = "johnwilliams",
                        content = "Brazilian barbecue is really unique!!",
                    ),
                    Comment(
                        authorId = "mikoto@email.com",
                        authorImage = R.drawable.perfil_japonesa,
                        authorName = "mikoto_k",
                        content = "冷たいビールはお肉によく合います！\uD83D\uDE0A",
                    ),
                    Comment(
                        authorId = "hector@email.com",
                        authorImage = R.drawable.perfil_espanhol,
                        authorName = "hector-garcia",
                        content = "¡Qué buena elección! Pero yo cambiaría la cerveza por vino, jajajaja. \uD83C\uDF77",
                    ),
                    Comment(
                        authorId = "andrei@email.com",
                        authorImage = R.drawable.perfil_russo,
                        authorName = "andrei22",
                        content = "Вот как нужно делать бразильское барбекю!\uD83E\uDD69\uD83D\uDD25",
                    ),
                    Comment(
                        authorId = "maria@email.com",
                        authorImage = R.drawable.perfil_libanesa,
                        authorName = "maria_j_n",
                        content = "هذه الصورة جعلتني أشعر بالجوع! \uD83D\uDE0B\uD83D\uDCF8",
                    ),
                    Comment(
                        authorId = "amelie@email.com",
                        authorImage = R.drawable.perfil_francesa,
                        authorName = "amelie-bernard",
                        content = "Tes posts de barbecue me donnent faim à chaque fois ! \uD83C\uDF56\uD83D\uDE04",
                    ),
                    Comment(
                        authorId = "lee@email.com",
                        authorImage = R.drawable.perfil_chines,
                        authorName = "mingLee",
                        content = "好想念巴西烤肉，这是世界上最棒的！",
                    )
                )
            ),
            Post(
                postId = 2,
                authorPostId = "michael@email.com",
                authorPostImage = R.drawable.perfil_alemao,
                authorPostName = "michael-muller",
                postImage = R.drawable.cars,
                postDescription = "Sportwagen sind nicht nur Maschinen – sie sind wahre Kunstwerke auf Rädern. Jede Kurve im Design, jedes Detail im Innenraum und jedes PS unter der Haube ist dafür gemacht, Herzen schneller schlagen zu lassen." +
                        "Ob auf der Straße oder im Stillstand – ein Sportwagen zieht immer alle Blicke auf sich." +
                        "Welches dieser Modelle würde in deine Traumgarage passen? \uD83C\uDFC1✨",
                postTotalLikes = 225,
                comments = listOf(
                    Comment(
                        authorId = "andrei@email.com",
                        authorImage = R.drawable.perfil_russo,
                        authorName = "andrei22",
                        content = "Какой шикарный автомобиль! Выглядит просто мощно и стильно.",
                    ),
                    Comment(
                        authorId = "mikoto@email.com",
                        authorImage = R.drawable.perfil_japonesa,
                        authorName = "mikoto_k",
                        content = "これが僕の夢の車です。",
                    )
                )
            ),
            Post(
                postId = 3,
                authorPostId = "park@email.com",
                authorPostImage = R.drawable.perfil_coreana,
                authorPostName = "parkYuna",
                postImage = R.drawable.cachorro,
                postDescription = "네 발 달린 나의 가장 친한 친구" +
                        "아무 말 하지 않아도 날 이해해주고, 항상 내 곁을 지켜줘. 힘든 날도 너 덕분에 훨씬 더 가벼워져. 세상에서 가장 믿음직한 내 반려자가 되어줘서 고마워 \uD83D\uDC36✨" +
                        "내 털복숭이, 사랑해 무한대로! \uD83D\uDC15",
                postTotalLikes = 89,
                comments = listOf(
                    Comment(
                        authorId = "maria@email.com",
                        authorImage = R.drawable.perfil_libanesa,
                        authorName = "maria_j_n",
                        content = "يا له من كلب جميل! إنه يشبه الثعلب الصغير.\uD83E\uDD8A",
                    ),
                    Comment(
                        authorId = "hector@email.com",
                        authorImage = R.drawable.perfil_espanhol,
                        authorName = "hector-garcia",
                        content = "¡Qué ternura! Ese perrito es simplemente adorable.",
                    ),
                    Comment(
                        authorId = "robson@email.com",
                        authorImage = R.drawable.perfil_brasileiro,
                        authorName = "robson_silva",
                        content = "Meu irmão tem um igual! Essa raça é muito carinhosa",
                    )
                )
            ),
            Post(
                postId = 4,
                authorPostId = "amelie@email.com",
                authorPostImage = R.drawable.perfil_francesa,
                authorPostName = "amelie-bernard",
                postImage = R.drawable.italy,
                postDescription = "Entre des murs anciens et des pas silencieux, chaque ruelle en Italie renferme des histoires que le temps murmure encore." +
                        "Marcher dans ces passages étroits, c’est plonger dans un passé vivant, où chaque pierre a une mémoire et chaque fenêtre cache un secret.",
                postTotalLikes = 120,
                comments = listOf(
                    Comment(
                        authorId = "john@email.com",
                        authorImage = R.drawable.perfil_americano,
                        authorName = "johnwilliams",
                        content = "What an amazing photo! The composition, the lighting... everything is just perfect",
                    ),
                    Comment(
                        authorId = "mikoto@email.com",
                        authorImage = R.drawable.perfil_japonesa,
                        authorName = "mikoto_k",
                        content = "写真が綺麗ですね！イタリアはとてもロマンチックな国です!!\uD83E\uDD70❤\uFE0F",
                    )
                )
            ),
            Post(
                postId = 5,
                authorPostId = "john@email.com",
                authorPostImage = R.drawable.perfil_americano,
                authorPostName = "johnwilliams",
                postImage = R.drawable.tucano,
                postDescription = "A wild encounter in the heart of the forest. Colorful, curious, and absolutely unforgettable.",
                postTotalLikes = 450,
                comments = listOf(
                    Comment(
                        authorId = "amelie@email.com",
                        authorImage = R.drawable.perfil_francesa,
                        authorName = "amelie-bernard",
                        content = "Comment as-tu pris cette photo ? Elle est tout simplement parfaite !!",
                    ),
                    Comment(
                        authorId = "michael@email.com",
                        authorImage = R.drawable.perfil_alemao,
                        authorName = "michael-muller",
                        content = "So ein wunderschöner Moment – der Tukan sieht aus wie aus einem Märchen.",
                    ),
                    Comment(
                        authorId = "lee@email.com",
                        authorImage = R.drawable.perfil_chines,
                        authorName = "mingLee",
                        content = "我特别喜欢在大自然中拍摄的动物照片，真的太美了！",
                    )
                )
            ),
            Post(
                postId = 6,
                authorPostId = "mikoto@email.com",
                authorPostImage = R.drawable.perfil_japonesa,
                authorPostName = "mikoto_k",
                postImage = R.drawable.newborn,
                postDescription = "まだこんなに小さいのに、あなたは私の世界をすっかり変えてくれた。" +
                        "あなたは私の一番大切な贈り物であり、光であり、地上に舞い降りた小さな天使。" +
                        "言葉では言い表せないほど、あなたを愛しているよ、私の娘。",
                postTotalLikes = 220,
                comments = listOf(
                    Comment(
                        authorId = "amelie@email.com",
                        authorImage = R.drawable.perfil_francesa,
                        authorName = "amelie-bernard",
                        content = "Ton bébé est absolument adorable ! Je suis tellement heureuse pour toi, ma chère amie. Quelle merveille !",
                    ),
                    Comment(
                        authorId = "park@email.com",
                        authorImage = R.drawable.perfil_coreana,
                        authorName = "parkYuna",
                        content = "아기가 정말 사랑스러워요! 이렇게 예쁜 천사를 보게 돼서 너무 기뻐요. 진심으로 축하해요!",
                    ),
                    Comment(
                        authorId = "lee@email.com",
                        authorImage = R.drawable.perfil_chines,
                        authorName = "mingLee",
                        content = "宝宝长得真像妈妈，太可爱了！",
                    )
                )
            ),
            Post(
                postId = 7,
                authorPostId = "maria@email.com",
                authorPostImage = R.drawable.perfil_libanesa,
                authorPostName = "maria_j_n",
                postImage = R.drawable.airplane,
                postDescription = "السفر ليس مجرد تغيير في المكان، بل هو تغيير في الداخل أيضًا." +
                        "كل وجهة تعلّمني شيئًا جديدًا، وكل طريق يقودني لأقترب أكثر من نفسي." +
                        "أنا مكوّنة من خرائط وذكريات ومناظر غيّرتني إلى الأبد.",
                postTotalLikes = 536,
                comments = listOf(
                    Comment(
                        authorId = "andrei@email.com",
                        authorImage = R.drawable.perfil_russo,
                        authorName = "andrei22",
                        content = "Обожаю сидеть у окна, когда лечу на самолёте. Смотреть на облака и города внизу — это волшебство!",
                    )
                )
            ),
            Post(
                postId = 8,
                authorPostId = "lee@email.com",
                authorPostImage = R.drawable.perfil_chines,
                authorPostName = "mingLee",
                postImage = R.drawable.beach,
                postDescription = "没有什么比海滩的一天更能让人忘记烦恼，想起生活其实可以很有趣。阳光、大海、沙滩，零烦恼。就是这个感觉！\uD83D\uDE0E\uD83C\uDF0A",
                postTotalLikes = 154,
                comments = listOf(
                    Comment(
                        authorId = "robson@email.com",
                        authorImage = R.drawable.perfil_brasileiro,
                        authorName = "robson_silva",
                        content = "Quando puder, venha visitar as praias do Brasil. Temos as melhores praias do mundo!",
                    ),
                    Comment(
                        authorId = "andrei@email.com",
                        authorImage = R.drawable.perfil_russo,
                        authorName = "andrei22",
                        content = "Какая потрясающая фотография! Море, солнце и песок — просто мечта.",
                    ),
                    Comment(
                        authorId = "maria@email.com",
                        authorImage = R.drawable.perfil_libanesa,
                        authorName = "maria_j_n",
                        content = "ما أجمل هذا الشاطئ! لون هذه المياه يجعلني أرغب في السباحة فوراً!",
                    ),
                )
            ),
            Post(
                postId = 9,
                authorPostId = "andrei@email.com",
                authorPostImage = R.drawable.perfil_russo,
                authorPostName = "andrei22",
                postImage = R.drawable.asia,
                postDescription = "Япония — это просто другой уровень." +
                        "В её храмах, горах и даже в том, как всё устроено с уважением и заботой, есть тихая, особенная красота." +
                        "Это то место, где хочется сбавить темп и взглянуть на мир по-новому.",
                postTotalLikes = 75,
                comments = listOf(
                    Comment(
                        authorId = "mikoto@email.com",
                        authorImage = R.drawable.perfil_japonesa,
                        authorName = "mikoto_k",
                        content = "なんて素敵な写真！ここは日本で一番好きな場所です。",
                    ),
                    Comment(
                        authorId = "amelie@email.com",
                        authorImage = R.drawable.perfil_francesa,
                        authorName = "amelie-bernard",
                        content = "Mon rêve, c'est de découvrir le Japon ! Je vais ajouter cet endroit à mon itinéraire de voyage.",
                    )
                )
            ),
            Post(
                postId = 10,
                authorPostId = "hector@email.com",
                authorPostImage = R.drawable.perfil_espanhol,
                authorPostName = "hector-garcia",
                postImage = R.drawable.pasta,
                postDescription = "Cuando uno acierta en la cocina, el orgullo es real. " +
                        "Preparé este plato con mis propias manos y quedó simplemente increíble. " +
                        "Nada como una buena comida casera — con sabor, cariño y ese toque especial.",
                postTotalLikes = 422,
                comments = listOf(
                    Comment(
                        authorId = "john@email.com",
                        authorImage = R.drawable.perfil_americano,
                        authorName = "johnwilliams",
                        content = "Seriously, this dish turned out amazing! You’re really talented in the kitchen — I’d love to try this again sometime!",
                    ),
                    Comment(
                        authorId = "park@email.com",
                        authorImage = R.drawable.perfil_coreana,
                        authorName = "parkYuna",
                        content = "나중에 꼭 레시피 보내줘! 나 이탈리아 음식 정말 좋아해!",
                    ),
                    Comment(
                        authorId = "michael@email.com",
                        authorImage = R.drawable.perfil_alemao,
                        authorName = "michael-muller",
                        content = "Teile das Rezept mit uns, mein Freund! Schon beim Anblick dieses Fotos bekomme ich Hunger.",
                    ),
                    Comment(
                        authorId = "andrei@email.com",
                        authorImage = R.drawable.perfil_russo,
                        authorName = "andrei22",
                        content = "Фото просто потрясающее, но вкус блюда, наверное, ещё лучше!",
                    )
                )
            )
        )
        val postsList
            get() = _postsList.toList()
    }

    fun getPosts(): Flow<List<Post>> = flow {
        emit(_postsList)
    }

    fun setLikePost(post: Post) {
        val currentPostList = _postsList.toMutableList()
        currentPostList.indexOfFirst { it.postId == post.postId }.takeIf { it != -1 }
            ?.let { index ->
                val updatedPost = if (currentPostList[index].postIsLiked) {
                    currentPostList[index].copy(
                        postIsLiked = false,
                        postTotalLikes = currentPostList[index].postTotalLikes - 1,
                    )
                } else {
                    currentPostList[index].copy(
                        postIsLiked = true,
                        postTotalLikes = currentPostList[index].postTotalLikes + 1,
                    )
                }
                currentPostList[index] = updatedPost
                _postsList = currentPostList
            }
    }

    fun setLikeComment(commentId: String, postId: Long) {
        val postsList = _postsList.map { post ->
            if (post.postId == postId) {
                post.copy(comments = post.comments.map { comment ->
                    if (comment.commentId == commentId) {
                        if (comment.isCommentLiked) {
                            comment.copy(
                                isCommentLiked = false,
                                commentTotalLikes = comment.commentTotalLikes - 1
                            )
                        } else {
                            comment.copy(
                                isCommentLiked = true,
                                commentTotalLikes = comment.commentTotalLikes + 1
                            )
                        }
                    } else comment
                }
                )
            } else {
                post
            }
        }.toMutableList()
        _postsList = postsList
    }

    fun getPostById(postId: Long) = flow {
        Log.i("Samples", "postList: $_postsList")
        val postsList = _postsList.toMutableList()
        emit(postsList.find { it.postId == postId })
    }

    private val userProfile = User(
        userId = "james@email.com",
        userName = "james_137",
        userImage = R.drawable.user_photo
    )

    fun getUserProfile() = flow {
        emit(userProfile)
    }

    fun addNewCommentToPostCommentsList(postId: Long, newComment: Comment) {
        val updatedPostsList = _postsList.map { post ->
            if (post.postId == postId) {
                val updatedPostCommentsList = post.comments.toMutableList()
                updatedPostCommentsList.add(newComment)
                post.copy(comments = updatedPostCommentsList)
            } else post
        }.toMutableList()
        _postsList = updatedPostsList
    }
}
