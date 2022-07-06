package home.task.moonactive.from.shay;

import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void FormTextToWrite(ConvertingJsonToClasses.JsonResponse jsonDataJoke) {

        String textToWrite = "Description: " + jsonDataJoke.contents.getJokes().get(0).getDescription() + "\n".
                concat("Category: " + jsonDataJoke.contents.getJokes().get(0).getCategory() + "\n")
                .concat("Title: " + jsonDataJoke.contents.getJokes().get(0).joke.getTitle() + "\n")
                .concat("Text: " + jsonDataJoke.contents.getJokes().get(0).joke.getText() + "\n");

        try (FileWriter writer = new FileWriter(jsonDataJoke.contents.getJokes().get(0).joke.getId(), false)) {
            writer.write(textToWrite);
            writer.flush();
            writer.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}