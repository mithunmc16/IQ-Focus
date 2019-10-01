package comp1110.ass2;

public enum State {
    G,
    W,
    R,
    B;

    public static State stateFromCharacter(char character){
        if(character == 'G'){
            return G;
        }

        else if(character == 'W'){
            return W;
        }

        else if(character == 'R'){
            return R;
        }
        else{
            return B;
        }
    }
}
