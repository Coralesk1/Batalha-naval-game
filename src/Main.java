void main() {
    Scanner scanner = new Scanner(System.in);
    System.out.println("Welcome to Battle chip !");

    String[][] matrizBi = new String[10][10];

    System.out.println("Qual é a sua cordenada X ? :");
    int cordenadaX = scanner.nextInt();

    System.out.println("Qual é a sua cordenada Y ? :");
    int cordenadaY = scanner.nextInt();

    for(int i = 0; i < matrizBi.length; i++){
        for(int j = 0; j < matrizBi[0].length; j++){
            System.out.print(matrizBi[i][j] = "o" + " ");
            matrizBi[cordenadaX][cordenadaY] = "X";
        }
        System.out.println();
    }

    System.out.println("===================");

    for(int i = 0; i < matrizBi.length; i++){
        for(int j = 0; j < matrizBi[0].length; j++){
            System.out.print(matrizBi[i][j] = "0" + " ");
            matrizBi[cordenadaX][cordenadaY] = "X";
        }
        System.out.println();
    }









}

