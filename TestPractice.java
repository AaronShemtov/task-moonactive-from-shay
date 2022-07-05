package home.task.moonactive.from.shay;

import java.util.List;

public class TestPractice {

    public static class ClassCategory {

        public String name;
        public String description;
        public String language;
        public String background;

        public String getLanguage() {
            return language;
        }

        public void setLanguage(String language) {
            this.language = language;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getBackground() {
            return background;
        }

        public void setBackground(String background) {
            this.background = background;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public void printWholeJoke() {
            System.out.println("language: " + getLanguage());
            System.out.println("name: " + getName());
            System.out.println("background: " + getBackground());
            System.out.println("name: " + getDescription());
        }
    }

    public static class ClassJokes {

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getLanguage() {
            return language;
        }

        public void setLanguage(String language) {
            this.language = language;
        }

        public String getBackground() {
            return background;
        }

        public void setBackground(String background) {
            this.background = background;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public ClassJoke getJoke() {
            return joke;
        }

        public void setJoke(ClassJoke joke) {
            this.joke = joke;
        }

        public String description;
        public String language;
        public String background;
        public String category;
        public String date;
        public ClassJoke joke = new ClassJoke();

    }

    public static class ClassJoke {

        public String getRacial() {
            return racial;
        }

        public void setRacial(String racial) {
            this.racial = racial;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getLenght() {
            return length;
        }

        public void setLenght(String lenght) {
            this.length = lenght;
        }

        public String getLang() {
            return lang;
        }

        public void setLang(String lang) {
            this.lang = lang;
        }

        public String getClean() {
            return clean;
        }

        public void setClean(String clean) {
            this.clean = clean;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public String racial;
        public String title;
        public String date;
        public String length;
        public String lang;
        public String clean;
        public String id;
        public String text;
    }



    public static class ClassCategoeries {

        private List<ClassCategory> categories;
        private List<ClassJokes> jokes;
        private String copyright;

        public List<ClassJokes> getJokes() {
            return jokes;
        }

        public void setJokes(List<ClassJokes> jokes) {
            this.jokes = jokes;
        }

        public void printEachCategory() {
            for (ClassCategory joke : categories) {
                System.out.println();
                joke.printWholeJoke();
            }
        }

        public void setCategories(List<ClassCategory> categories) {
            this.categories = categories;
        }
        public List<ClassCategory> getCategories() {
            return categories;
        }
        public String getCopyright() {
            return copyright;
        }
        public void setCopyright(String copyright) {
            this.copyright = copyright;
        }

    }

    public static class ClassSuccess {
        Integer total;

        public Integer getTotal() {
            return total;
        }

        public void setTotal(Integer total) {
            this.total = total;
        }
    }

    public static class JsonResponse {

        public ClassCategoeries contents = new ClassCategoeries();
        public ClassSuccess success = new ClassSuccess();

        public ClassSuccess getSuccess() {
            return success;
        }

        public void setSuccess(ClassSuccess success) {
            this.success = success;
        }

        public ClassCategoeries getContents() {
            return contents;
        }
        public void setContents(ClassCategoeries contents) {
            this.contents = contents;
        }
    }

}