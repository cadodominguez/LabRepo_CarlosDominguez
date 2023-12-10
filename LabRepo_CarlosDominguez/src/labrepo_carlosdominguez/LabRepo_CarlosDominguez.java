
package labrepo_carlosdominguez;
import java.util.*; // la verdad esto me lo enseño un amigo
public class LabRepo_CarlosDominguez {
    static Random rm = new Random();
    static Scanner sc = new Scanner (System.in);
    
    public static void main(String[] args) {
        
        boolean end = false;
        while (end == false){
            System.out.println("");
            System.out.println("********MENU*******");
            System.out.println("1. Dungeons and Dragons");
            System.out.println("2. Laberinto");
            System.out.println("3. Salir");
            int opcion = sc.nextInt();
            switch (opcion){
                case 1 : {
                    
                    System.out.println("Eliga un personaje : ");
                    System.out.println("1. Caballero :\n Caracter : ' C '");
                    System.out.println("2. Mago :\n Caracter : ' M '");
                    int personaje = sc.nextInt();
                    int hp = 0;
                    int mp = 0;
                    String hero = null;
                    if (personaje == 1){
                        System.out.println("Usted a eleigo al Caballero ! ");
                        System.out.println("Caracter : 'C'\n Puntos de Vida:250 \n Energia:50");
                        hp = 250;
                        mp = 50;
                        hero = " C ";
                    }else if (personaje == 2){
                        System.out.println("Usted a eleigo al Mago ! ");
                        System.out.println("Caracter : 'M'\n Puntos de Vida:150 \n Energia:230");
                        hp = 150;
                        mp = 230;
                        hero = " M ";
                    }
                    int posx = 0;
                    int posy = 0;
                    String [][] tableroi = llenadoInicial(hero,posx,posy);
                    imprimirtabinicial (tableroi);
                    System.out.println("presiones Enter para continuar...");
                    int vacio = sc.nextInt();
                    juego (tableroi,hero,posx,posy,hp,mp);
                    break;
                    
                }

                case 2 : {
                    boolean victory =false;
                    char [][] maze = llenarmaze ();
                    int posx = 1;
                    int posy = 1;
                    while ( victory == false){
                        maze [posx][posy] = 'C';
                        System.out.println("-----MAZE-----");
                        imprimirtablamaze (maze);
                        System.out.println("Ingrese a donde sea moverse (w,s,a,d)");
                        char move = sc.next().charAt(0);
                        switch (move){
                            case 'w' : {
                                if (maze [posx-1][posy]=='#'){
                                    System.out.println("No se puede pasar por ahi");
                                }else{
                                    System.out.println("Se realizado el movimiento");
                                    maze [posx-1][posy] = 'C';
                                     posx=-1;
                                }
                                break;
                            }
                            case 'a' :{
                                if (maze [posx][posy-1]=='#'){
                                    System.out.println("No se puede pasar por ahi");
                                }else{
                                    System.out.println("Se realizado el movimiento");
                                    maze [posx][posy-1] = 'C';
                                }
                                break;
                            }
                            case 'd' : {
                                if (maze [posx][posy+1]=='#'){
                                    System.out.println("No se puede pasar por ahi");
                                }else{
                                    System.out.println("Se realizado el movimiento");
                                    maze [posx][posy+1] = 'C';
                                }
                                break;
                            }
                            case 's' : {
                                if (maze [posx+1][posy]=='#'){
                                    System.out.println("No se puede pasar por ahi");
                                }else{
                                    System.out.println("Se realizado el movimiento");
                                    maze [posx+1][posy] = 'C';
                                    posx=+1;
                                }
                                break;
                            }
                            default: {
                                System.out.println("Ingrese w,s,a,d");
                            }
                        }
                        if (maze [2][9] == 'C'){
                            System.out.println("Felicidades a logrado salir del laberinto :");
                        }
                    }
                }
                case 3 : {
                    System.out.println("Saliendo......");
                    end = true;
                    break;
                }
                default : {
                    System.out.println("El numero no es valido");
                    break;
                }
            }
        }
    }
    public static String [][] llenadoInicial (String hero,int posx, int posy){
        String [][] temporal = new String [10][10];
        for (int i = 0; i < temporal.length; i++){
            
            for (int j = 0 ; j < temporal.length; j++){
               // C o M es el personaje
               // H es el bono de vida
               // E es el bono de energia
               if (temporal [i][j] == temporal [posx][posy] ){
                   temporal [i][j] = " * ";
                   temporal [i][j] = hero;
               }else if (((i + j+1) % 2 == 0 && i % 2 == 0)&& j != 4 && j != 5){
                   temporal [i][j] = " H ";
               }else if (i == j || i + j == 10 - 1){
                   temporal [i][j] = " E ";
               }else{
                   temporal [i][j] = " - ";
               }
            } 
        }
        return temporal;
        
    }
    
    public static void imprimirtabinicial ( String [][] tableroi){
      for (int i = 0; i < tableroi.length; i++){
            for (int j = 0 ; j < tableroi.length; j++){
                System.out.print(tableroi [i][j]);
            }
            System.out.println();
        }  
    }
    
    public static void juego ( String [][] tableroi, String hero,int posx, int posy, int hp, int mp){
        char resp = 's';
        boolean deterfase = true;
        while (resp == 's' || resp == 'S'){
            while (deterfase == true){
                int dado = rm.nextInt((16-1)+1);
                System.out.println("Usted saco :" + dado);
                int posny = posy+dado;
                
                if (posny >= 10){
                    posy = posny-10;
                    tableroi [posx+1][posy] = hero;    
                }else if ( posny < 10){
                    if (posy+posny < 10){
                        tableroi [posx][posny] = hero;
                    }else{
                        tableroi [posx+1][posny-10] = hero;
                    }
                }
                /*int dif = 0;
                int mayor;
                int menor;
                if (posy > dado){
                    mayor = posy;
                    menor = dado;
                }else{
                    mayor = dado;
                    menor = posy;
                }
                if (posx+dado>10 ){
                    dif = mayor - menor;
                }else if (posy+dado <= 10){
                    tableroi [posx][posy] = " - ";
                    tableroi [posx][posy+dado] = personaje;
                    dif = 0;
                }
                if (dif != 0){
                    tableroi [posx][posy] = " - ";
                    tableroi [posx][posy+dif-10] = personaje;
                }
                */
                tableroi = llenadoInicial(hero,posx,posy);
                imprimirtabinicial (tableroi);
                boolean nothing = false;
                for (int i = 0; i < tableroi.length;i++){
                    for (int j = 0 ; j < tableroi[i].length; j++){
                        if (tableroi [i][j] == " C " || tableroi [i][j] == " M "){
                            if (((i + j+1) % 2 == 0 && i % 2 == 0) && i != 0 && j !=0){
                                System.out.println("Encontro un cofre con 20 de vida!");
                                hp =+ 20;
                            }else if ((i == j || i + j == 10 - 1)&& tableroi [i][j] == hero && i != 0 && j != 0 ){
                                System.out.println("Encontro Energia");
                                mp =+ 5;
                            }else{
                                nothing = true;
                            }
                        }
                    }
                }
                if (nothing == true){
                    System.out.println("No has encontrado nada");
                }
                boolean verf = verificar(tableroi);
                if (verf == true){
                    System.out.println("No te has encontrado con ningun dragon");
                }else if (verf == false){
                    int dragones = dragonN(tableroi);
                    pelea(dragones,hp,mp);
                }
                System.out.println("Stats actuales :");
                System.out.println("Hp : "+hp);
                System.out.println("Mp : "+mp);
                if (mp <= 0){
                    mp = 0;
                }
                if (hp <= 0){
                    mp = 0;
                    hp = 0;
                    System.out.println("Perdiste");
                    deterfase = false;
                }
                if (tableroi[9][9] == " C " || tableroi[9][9] == " M "){
                    System.out.println("Completaste la mazmorra ");
                    deterfase = false;
                }
            }
            System.out.println("Tus stats finales fueron :");
            System.out.println("Hp : "+hp);
            System.out.println("Energia : " + mp);
            System.out.println("Desea continuar (S/N) : ");
            resp = sc.next().charAt(0);
        }
    }
    
    public static boolean verificar (String [][] tableroi){
        boolean temporal = false;
        for (int i = 0; i < tableroi.length; i++){
            for (int j = 0 ; j < tableroi[i].length; j++){
                if ((i+j)%2==0){
                    temporal = true;
                }else{
                    temporal = false;
                }
            }
        }  
        return temporal;
    }
    
    public static char [][] llenarmaze (){
        char [][] temporal = {
            {'#', '#', '#', '#', '#', '#', '#', '#', '#', '#'},
            {'#', ' ', '#', ' ', ' ', ' ', '#', '#', ' ', '#'},
            {'#', ' ', '#', ' ', '#', '#', '#', ' ', ' ', ' '},
            {'#', ' ', '#', ' ', ' ', ' ', '#', ' ', '#', '#'},
            {'#', ' ', ' ', ' ', '#', ' ', '#', ' ', ' ', '#'},
            {'#', ' ', '#', ' ', '#', ' ', '#', '#', ' ', '#'},
            {'#', ' ', '#', ' ', '#', ' ', ' ', ' ', ' ', '#'},
            {'#', '#', '#', '#', '#', '#', '#', '#', '#', '#'}       
        };
        return temporal;
    }
    
    public static void imprimirtablamaze ( char [][] tableroi){
      for (int i = 0; i < tableroi.length; i++){
            for (int j = 0 ; j < tableroi[i].length; j++){
                System.out.print(tableroi [i][j]);
            }
            System.out.println();
        }  
    }
    public static int dragonN (String [][] tableroi){
        int num = 0;
        for (int i = 0; i < tableroi.length; i++){
            for (int j = 0 ; j < tableroi[i].length; j++){
                switch (i){
                    case 0 :{
                        num = 2;
                        break;
                    }
                    case 1 :{
                        num = 2;
                        break;
                    }
                    case 2 :{
                        num = 2;
                        break;
                    }
                    case 3 :{
                        num = 3;
                        break;
                    }
                    case 4 :{
                        num = 3;
                        break;
                    }
                    case 5 :{
                        num = 3;
                        break;
                    }
                    case 6 :{
                        num = 4;
                        break;
                    }
                    case 7 :{
                        num = 4;
                        break;
                    }
                    case 8 :{
                        num = 4;
                        break;
                    }
                    case 9 :{
                        num = 5;
                        break;
                    }
                }
            }
        }  
        return num;
    }
    
    public static void pelea (int dragones,int hp,int mp) {
        System.out.println("Te as encontrado con " + dragones + "Dragones!!!");
        int prob = rm.nextInt((100-1)+1);
        if (prob>=50){
            System.out.println("El jugador gano la pelea ");
            mp =- 5*dragones;
        }else{
            System.out.println("El jugador a escapado");
            hp =- 25*dragones;
        }
    }
    
}
