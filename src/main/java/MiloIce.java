public class MiloIce {
    public static void main(String[] args) {

        String logo = " __    __   __   __        ____       _______    _____   ______        \n"
             + "|  \\  /  | |  | |  |      / __ \\     |__   __|  /   __| |  ____|       \n"
             + "|   \\/   | |  | |  |     | /  \\ |       | |    |   /    | |____        \n"
             + "|   ||   | |  | |  |     | |  | |       | |    |  |     |  ____|       \n"
             + "|   ||   | |  | |  |___  | \\__/ |     __| |__  |   \\__  | |____        \n"
             + "|___||___| |__| |______|  \\____/     |_______|  \\_____| |______|       ";
        System.out.println(logo);
        for (int i = 0; i < 30; i++) {
            System.out.print("_");
        }

        System.out.print("\n" + """
                Hello! I'm Milo Ice
                What can I do for you?m
                """);
        for (int i = 0; i < 30; i++) {
            System.out.print("_");
        }
        System.out.print("\n" + """
                Bye. Hope to see you again soon!
                """);
        for (int i = 0; i < 30; i++) {
            System.out.print("_");
        }
    }
}
