package nl.brambogaerts.liminal.progress;

public class ProgressBar {
	double total, done;

	public ProgressBar(String label, double total) {
		this.total = total;
		System.out.println("\n" + label + "\n");
		display();
	}

	public void setProgress(double done){
		if(done != this.done){
			this.done = done;
			display();

			if(done == total){
				finish();
			}
		}
	}

	private void finish(){
		System.out.print("\n");
	}

	private void display(){
		long percent = Math.round(done / total * 100);

		System.out.print("\r" + percent + "%");
		if(percent < 10) System.out.print("    ");
		else if(percent < 100) System.out.print("   ");
		else System.out.print("  ");

		for(int j=0; j<percent; j++)
		System.out.print("\u2588");
		for(int j=0; j<100-percent; j++)
		System.out.print("\u2592");
	}
}
