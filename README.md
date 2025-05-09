![Redegram_Image](https://github.com/user-attachments/assets/90341045-0f7c-4844-b1d9-88a199e8e3c5)

<h1>Project Description</h1>

Redegram is an app created to test the features offered by the **ML Kit Language Identification** and **Language Translation APIs**.
The app simulates a social network that displays a feed of posts with photos, descriptions, a comment box, and the option to like posts and comments.
The posts and comments are written in different languages, and using the ML Kit APIs, it is possible to translate them into the language that the mobile device is configured to.

<h1>Features</h1>

- `Post List`: On the home screen, a list of posts is displayed with a photo, description, comment box, and a like counter for the post.
- `Like Posts`: Each post includes the option to like or unlike it by clicking the "heart icon".
- `Access Comment Box`: Each post includes a comment box that can be accessed by clicking the "comment icon".
- `Like Comments`: Each comment includes the option to like or unlike it by clicking the "heart icon".
- `Add Comments`: It's possible to add comments to each post.
- `Post Description Language Identification and Translation`: By clicking **"See translation"**, you can identify and translate the language of the post descriptions into the language the mobile device is set to.
- `Return Description to Original Post Language`: You can also revert the translated description back to the post's original language by clicking **"See original"**.
- `Comment Language Identification and Translation`: By clicking **"See translation"**, you can identify and translate the language of post comments into the language the mobile device is set to.
- `Return Comment to Original Post Language`: You can also revert the translated comment back to the original language by clicking **"See original"**.

![Image](https://github.com/user-attachments/assets/e7c282a9-a29f-4805-9d88-d87da8f4f0c7)

<h1>Techniques and technologies used.</h1>

The app was developed with the following technologies:

- `Dagger-Hilt`: Dependecy Injection
- `Jetpack Compose`: User interface implementation
- `ViewModel e uiState`: Screen state management
- `Navigation com NavHost`: Navigation between screens via graphs hosted in a NavHost
- `Coroutines e Flow`: Running operations asynchronously and reactively
- `Coil`: Image upload
- `ML Kit Language Identification API`: Identify the language of texts
- `ML Kit Language Translation API`: Translate the language of texts

<h1>Access to the Project</h1>

You can access the [project source code](https://github.com/StevenMTung/Redegram) or [download it](https://github.com/StevenMTung/Redegram/archive/refs/heads/main.zip).

<h1>Open and Run the Project</h1> 

After downloading the project, you can open it with `Android Studio`. To do this, on the launcher screen click on:

- `Open an Existing Project` (or a similar option);
- Find the location where the project is and select it (If the project was downloaded as a zip, you will need to extract it before locating it);
- Finally, click `OK`.

`Android Studio` will execute some Gradle tasks to set up the project, wait for them to finish. Once the tasks are completed, you can run the app 🏆 

<h1>Author</h1>

 [<img loading="lazy" src="https://avatars.githubusercontent.com/u/134224337?v=4" width=115><br><sub>Steven Marc Tung</sub>](https://github.com/StevenMTung)
| :---: | 

