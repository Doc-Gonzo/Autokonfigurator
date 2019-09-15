public class Package {

	private String packagePreis;
	private String packageLieferZeit;
	private String packageName;	
	private String packageItems;	
	
	/* Konstruktor */
	public Package(String packagePreis, String packageLieferZeit, String packageName, String packageItems) {
		super();
		this.packagePreis = packagePreis;
		this.packageLieferZeit = packageLieferZeit;
		this.packageName = packageName;
		this.packageItems = packageItems;
	}


	public String getPackagePreis() {
		return packagePreis;
	}
	public void setPackagePreis(String packagePreis) {
		this.packagePreis = packagePreis;
	}
	public String getPackageLieferZeit() {
		return packageLieferZeit;
	}
	public void setPackageLieferZeit(String packageLieferZeit) {
		this.packageLieferZeit = packageLieferZeit;
	}
	public String getPackageName() {
		return packageName;
	}
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	public String getPackageItems() {
		return packageItems;
	}
	public void setPackageItems(String packageItems) {
		this.packageItems = packageItems;
	}	
}
