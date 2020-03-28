package it.polito.tdp.corsi.model;

public class TestModel {

	private Model model = new Model();

	public static void main(String[] args) {
		TestModel tm = new TestModel();
		tm.run();
	}

	private void run() {
		System.out.println(model.getIscrittiByPeriodo(1));
	}

}
