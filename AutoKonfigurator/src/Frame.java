public class Frame {
	
	private String framePreis;
	private String frameLieferZeit;
	private String frameName;
	
	/* Konstruktor */
	public Frame(String lineValues, String lineValues2, String frameName) {
		super();
		this.framePreis = lineValues;
		this.frameLieferZeit = lineValues2;
		this.frameName = frameName;
	}
	public String getFramePreis() {
		return framePreis;
	}
	public void setFramePreis(String framePreis) {
		this.framePreis = framePreis;
	}
	public String getFrameLieferZeit() {
		return frameLieferZeit;
	}
	public void setFrameLieferZeit(String frameLieferZeit) {
		this.frameLieferZeit = frameLieferZeit;
	}
	public String getFrameName() {
		return frameName;
	}
	public void setFrameName(String frameName) {
		this.frameName = frameName;
	}

}
