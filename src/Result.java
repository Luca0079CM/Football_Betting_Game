public class Result {
    private int matchCode;
    private String result;

    public Result(int matchCode, String result){
        this.matchCode = matchCode;
        this.result = result;
    }

    public int getMatchCode(){
        return matchCode;
    }

    public String getResult(){
        return result;
    }
}
