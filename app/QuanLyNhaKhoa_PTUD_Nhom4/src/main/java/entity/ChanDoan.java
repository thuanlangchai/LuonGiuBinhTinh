package entity;

public class ChanDoan {

    private String maChanDoan;
    private String tenChanDoan;

    public ChanDoan() {
    }

    public ChanDoan(String maChanDoan, String tenChanDoan) {
        this.maChanDoan = maChanDoan;
        this.tenChanDoan = tenChanDoan;
    }

    public String getMaChanDoan() {
        return maChanDoan;
    }

    public void setMaChanDoan(String maChanDoan) {
        this.maChanDoan = maChanDoan;
    }

    public String getTenChanDoan() {
        return tenChanDoan;
    }

    public void setTenChanDoan(String tenChanDoan) {
        this.tenChanDoan = tenChanDoan;
    }
}
