![Redegram_Image](https://github.com/user-attachments/assets/90341045-0f7c-4844-b1d9-88a199e8e3c5)

<h1>Descrição do projeto</h1>

O Redegram é um app criado com o intuito de testar as funcionalidades oferecidas pelas **APIs de Identificação de Idiomas e Tradução de Idiomas do ML Kit**. 
O app simula uma rede social que apresenta um mural de postagens com fotos, descrição, caixa de comentários e opção de curtir as postagens e os comentários. 
As postagens e os comentários foram feitos em idiomas diferentes e a partir das APIs do ML Kit é possível fazer a tradução para o idioma que o dispositivo móvel está configurado.  

<h1>Funcionalidades</h1>

- `Lista de Posts`: Na tela inicial é exibido uma lista de publicações com foto, descrição, caixa de comentários e um contador de curtidas do post.
- `Curtir Posts`: Cada publicação apresenta a opção de marcar ou desmarcar a curtida clicando no "ícone do coração".
- `Acessar Caixa de Comentários`: Cada publicação apresenta uma caixa de comentários que pode ser acessada clicando no "ícone de comentários".
- `Curtir Comentários`: Cada comentário apresenta a opção de marcar ou desmarcar a curtida clicando no "ícone do coração".
- `Identificação e Tradução de Idiomas da Descrição do Post`: Ao clicar em **"Ver tradução"** é possível identificar e traduzir o idioma da descrição das publicações para o idioma em que o dispositivo móvel está configurado.
- `Retornar a Descrição para o idioma Original do Post`: Também é possível retornar a descrição traduzida para o idioma original da publicação ao clicar em **"Ver original"**.
- `Identificação e Tradução de Idiomas dos Comentários do Post`: Ao clicar em **"Ver tradução"** é possível identificar e traduzir o idioma dos comentários das publicações para o idioma em que o dispositivo móvel está configurado.
- `Retornar o Comentário para o idioma Original do Post`: Também é possível retornar o comentário traduzido para o idioma original da publicação ao clicar em **"Ver original"**.  

<h1>Técnicas e tecnologias utilizadas</h1>

O App foi desenvolvido com as seguintes tecnologias:

- `Dagger-Hilt`: Injeção de dependência
- `Jetpack Compose`: Implementação da interface de usuário
- `ViewModel e uiState`: Gerenciamento de Estados de tela
- `Navigation com NavHost`: Navegações entre telas por grafos hospedados em um NavHost
- `Coroutines e Flow`: Rodar as operações de forma assíncrona e reativas
- `Coil`: Upload de imagens
- `ML Kit Identificação de Idiomas`: Identificar o idioma de textos
- `ML Kit Tradução de Idiomas`: Traduzir o idioma de textos
