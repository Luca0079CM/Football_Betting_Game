class Result {
    private int matchCode;
    private String result;

    Result(int matchCode, String result){
        this.matchCode = matchCode;
        this.result = result;
    }

    int getMatchCode(){
        return matchCode;
    }

    String getResult(){
        return result;
    }
}
