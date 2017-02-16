package net.vompi;

/**
 * Created by dani on 1/3/17.
 */
public class Map {
    private int numberOfPlayers;
    public final Field data[][] = new Field[16][16];

    public Map(int numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
        if(numberOfPlayers == 4){

            //base floor with good fields
            for(int i = 0; i < 15; i++){
                for(int j = 0; j < 15; j++){
                    Field attainableField = new NormalAttainableField(i, j);
                    data[i][j] = attainableField;
                }
            }

            //first floor
            for (int i = 6; i < 9; i++) {
                for(int j = 6; j < 9; j++){
                    Field firstFloorField = new FirstFloorField(i, j);
                    data[i][j] = firstFloorField;
                }
            }

            //the center
            Field centerField = new CenterField();
            data[7][7] = centerField;
            
            //big empty squares
            for(int i = 0; i < 3; i++){
                for(int j = 0; j < 3; j++){
                    Field notAttainableField = new NotAttainableField(i, j);
                    data[i][j] = notAttainableField;
                }
            }
            for(int i = 12; i < 15; i++){
                for(int j = 0; j < 3; j++){
                    Field notAttainableField = new NotAttainableField(i, j);
                    data[i][j] = notAttainableField;
                }
            }
            for(int i = 0; i < 3; i++){
                for(int j = 12; j < 15; j++){
                    Field notAttainableField = new NotAttainableField(i, j);
                    data[i][j] = notAttainableField;
                }
            }
            for(int i = 12; i < 15; i++){
                for(int j = 12; j < 15; j++){
                    Field notAttainableField = new NotAttainableField(i, j);
                    data[i][j] = notAttainableField;
                }
            }

            //empty lines near squares
            for(int i = 0; i < 3; i++){
                Field notAttainableField = new NotAttainableField(i, 3);
                data[i][3] = notAttainableField;
            }
            for(int i = 0; i < 3; i++){
                Field notAttainableField = new NotAttainableField(3, i);
                data[3][i] = notAttainableField;
            }
            for(int i = 0; i < 3; i++){
                Field notAttainableField = new NotAttainableField(i, 11);
                data[i][11] = notAttainableField;
            }
            for(int i = 12; i < 15; i++){
                Field notAttainableField = new NotAttainableField(i, 3);
                data[i][3] = notAttainableField;
            }
            for(int i = 0; i < 3; i++){
                Field notAttainableField = new NotAttainableField(11, i);
                data[11][i] = notAttainableField;
            }
            for(int i = 12; i < 15; i++){
                Field notAttainableField = new NotAttainableField(3, i);
                data[3][i] = notAttainableField;
            }
            for(int i = 12; i < 15; i++){
                Field notAttainableField = new NotAttainableField(i, 11);
                data[i][11] = notAttainableField;
            }
            for(int i = 12; i < 15; i++){
                Field notAttainableField = new NotAttainableField(11, i);
                data[11][i] = notAttainableField;
            }

            //the lakes
            for(int i = 6; i < 9; i++){
                Field notAttainableField = new NotAttainableField(4, i);
                data[4][i] = notAttainableField;
            }
            for(int i = 6; i < 9; i++){
                Field notAttainableField = new NotAttainableField(i, 4);
                data[i][4] = notAttainableField;
            }
            for(int i = 6; i < 9; i++){
                Field notAttainableField = new NotAttainableField(i, 10);
                data[i][10] = notAttainableField;
            }
            for(int i = 6; i < 9; i++){
                Field notAttainableField = new NotAttainableField(10, i);
                data[10][i] = notAttainableField;
            }

            //resurrection fields
            Field resurrectionField1 = new ResurrectionField(7, 1);
            data[7][1] = resurrectionField1;
            Field resurrectionField2 = new ResurrectionField(1, 7);
            data[1][7] = resurrectionField2;
            Field resurrectionField3 = new ResurrectionField(7, 13);
            data[7][13] = resurrectionField3;
            Field resurrectionField4 = new ResurrectionField(13, 7);
            data[13][7] = resurrectionField4;

            //flag owner fields
            Field flagOwnerField1 = new FlagOwnerStartField(7, 0);
            data[7][0] = flagOwnerField1;
            Field flagOwnerField2 = new FlagOwnerStartField(0, 7);
            data[0][7] = flagOwnerField2;
            Field flagOwnerField3 = new FlagOwnerStartField(7, 14);
            data[7][14] = flagOwnerField3;
            Field flagOwnerField4 = new FlagOwnerStartField(14, 7);
            data[14][7] = flagOwnerField4;

            System.out.println("Successfully generated map!");
        } else {
            System.out.println("This kind of map is not available yet");
        }
    }

    public Field getField(int i, int j){
        return data[i][j];
    }
}
