import java.util.Scanner;

public class Gobang {

    private static final String PLAYER_BLACK = "黑方";
    private static final String PLAYER_WHITE = "白方";
    private static final String TIP_EXAMPLE = "eg: 3,3代表第三行第三列";

    public static void main(String[] args) {

        Board board = new Board();

        //从命令行获取size参数
        if (args.length > 0) {
            try {
                int size = Integer.parseInt(args[0]);
                board = new Board(size);
            } catch (NumberFormatException e) {
                System.out.println("初始化参数不合法");
            }
        }

        board.print();

        //用户输入
        System.out.println("请输入你要下的位置，" + TIP_EXAMPLE);
        System.out.println(PLAYER_WHITE + "先手");

        boolean isWhite = true;
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String cmd = scanner.nextLine();
            String[] posStrList = cmd.split("[,，]");
            System.out.println(cmd);
            if (posStrList.length >= 2) {
                try {
                    int row = Integer.parseInt(posStrList[0]) - 1;
                    int col = Integer.parseInt(posStrList[1]) - 1;
                    boolean success = board.setPiece(row, col, isWhite);
                    if (success) {
                        isWhite = !isWhite;
                    }
                    String player;
                    if (isWhite) {
                        player = PLAYER_WHITE;
                    } else {
                        player = PLAYER_BLACK;
                    }
                    System.out.println("轮到" + player + "下棋");
                } catch (NumberFormatException e) {
                    System.out.println("指令错误，" + TIP_EXAMPLE);
                }
            } else {
                System.out.println("指令错误，" + TIP_EXAMPLE);
            }
        }
    }
}
