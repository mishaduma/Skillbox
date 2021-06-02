public class Main {
    public static void main(String[] args) {
        String text = "I'm listening my own voice, I'm looking for my face. I want to find my right way, but I can't leave this place. I like your crazy red hair, I love your perfect smile. I want to go away from here, I want to see your eyes. But I'm caged and I can't use my love magic. I need to feel your heart beating, I need to hear your soul screaming. I need this all. But I'm caged, and want to use your love magic to break the walls of my cage-room, you should know that I need you. But you far away and I'm caged!";
        String[] words = text.replaceAll("\\p{Punct}", "").split(" ");
        for (int i = 0; i < words.length; i++) {
            System.out.println(words[i]);
        }
    }
}