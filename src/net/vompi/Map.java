package net.vompi;

/**
 * Created by dani on 1/3/17.
 */
public class Map {
    private int numberOfPlayers;
    public final Field data[][][] = new Field[16][16][2];

    public Map(int numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
        if(numberOfPlayers == 4){

            //base floor with good fields
            for(int i = 0; i < 15; i++){
                for(int j = 0; j < 15; j++){
                    Field attainableField = new Field(true, i, j, false);
                    data[i][j][0] = attainableField;
                }
            }

            //big empty squares
            for(int i = 0; i < 3; i++){
                for(int j = 0; j < 3; j++){
                    Field notAttainableField = new Field(false, i, j, false);
                    data[i][j][0] = notAttainableField;
                }
            }
            for(int i = 12; i < 15; i++){
                for(int j = 0; j < 3; j++){
                    Field notAttainableField = new Field(false, i, j, false);
                    data[i][j][0] = notAttainableField;
                }
            }
            for(int i = 0; i < 3; i++){
                for(int j = 12; j < 15; j++){
                    Field notAttainableField = new Field(false, i, j, false);
                    data[i][j][0] = notAttainableField;
                }
            }
            for(int i = 12; i < 15; i++){
                for(int j = 12; j < 15; j++){
                    Field notAttainableField = new Field(false, i, j, false);
                    data[i][j][0] = notAttainableField;
                }
            }

            //empty lines near squares
            for(int i = 0; i < 3; i++){
                Field notAttainableField = new Field(false, i, 3, false);
                data[i][3][0] = notAttainableField;
            }
            for(int i = 0; i < 3; i++){
                Field notAttainableField = new Field(false, 3, i, false);
                data[3][i][0] = notAttainableField;
            }
            for(int i = 0; i < 3; i++){
                Field notAttainableField = new Field(false, i, 11, false);
                data[i][11][0] = notAttainableField;
            }
            for(int i = 12; i < 15; i++){
                Field notAttainableField = new Field(false, i, 3, false);
                data[i][3][0] = notAttainableField;
            }
            for(int i = 0; i < 3; i++){
                Field notAttainableField = new Field(false, 11, i, false);
                data[11][i][0] = notAttainableField;
            }
            for(int i = 12; i < 15; i++){
                Field notAttainableField = new Field(false, 3, i, false);
                data[3][i][0] = notAttainableField;
            }
            for(int i = 12; i < 15; i++){
                Field notAttainableField = new Field(false, i, 13, false);
                data[i][12][0] = notAttainableField;
            }
            for(int i = 12; i < 15; i++){
                Field notAttainableField = new Field(false, 13, i, false);
                data[12][i][0] = notAttainableField;
            }

            //the lakes
            for(int i = 7; i < 10; i++){
                Field notAttainableField = new Field(false, 5, i, false);
                data[5][i][0] = notAttainableField;
            }
            for(int i = 7; i < 10; i++){
                Field notAttainableField = new Field(false, i, 5, false);
                data[i][5][0] = notAttainableField;
            }
            for(int i = 7; i < 10; i++){
                Field notAttainableField = new Field(false, i, 11, false);
                data[i][11][0] = notAttainableField;
            }
            for(int i = 7; i < 10; i++){
                Field notAttainableField = new Field(false, 11, i, false);
                data[11][i][0] = notAttainableField;
            }

            //floor layer
            for(int i = 0; i < 15; i++){
                for(int j = 0; j < 15; j++){
                    Field notAttainableField = new Field(false, i, j, false);
                    data[i][j][1] = notAttainableField;
                }
            }
            System.out.println("Successfully generated map!");
        } else {
            System.out.println("Not available yet");
        }
    }
}
