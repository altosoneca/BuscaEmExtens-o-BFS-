import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class Grafo {
	Map<String, LinkedList<String>> vetor;
	public int tempo = 0;

	public Grafo(int n) {
		vetor = new HashMap<String, LinkedList<String>>(n);
	}

	public void addAresta(String indice, String destino) {
		LinkedList<String> list = new LinkedList<String>();
		if (vetor.containsKey(indice)) {
			list = vetor.get(indice);
		}
		list.push(destino);
		vetor.put(indice, list);
	}

	public void showSucessores() {
		for (String vertice : vetor.keySet()) {
			System.out.print("Sucessores do vértice " + vertice + ": ");
			for (String adjacentes : vetor.get(vertice)) {
				System.out.print(adjacentes + " ");
			}
			System.out.println();
		}
	}

	public boolean arcoExiste(String a, String b) {
		return vetor.get(a).contains(b) || vetor.get(b).contains(a);

	}

	public Map<String, LinkedList<String>> getVetor() {
		return vetor;
	}

	public void buscaExtensão(Grafo g, String vertice) {
		List<String> fila = new LinkedList<String>();

		// creating a hash table
		Map<String, String> cor = new HashMap<String, String>();
		Map<String, String> ante = new HashMap<String, String>();
		Map<String, Integer> d = new HashMap<String, Integer>();

		for (String u : g.vetor.keySet()) {
			cor.put(u, "branco");
			ante.put(u, null);
			d.put(u, Integer.MAX_VALUE);
		}

		fila.add(vertice);
		cor.replace(vertice, "cinza");
		ante.replace(vertice, null);
		d.replace(vertice, 0);

		while (!fila.isEmpty()) {
			String now = fila.remove(0);

			for (String adj : g.vetor.get(now)) {
				if (cor.get(adj).compareTo("branco") == 0) {
					fila.add(adj);
					cor.replace(adj, "cinza");
					ante.replace(adj, now);
					d.replace(adj, d.get(now) + 1);
				}
				cor.replace(now, "preto");

			}

		}

		System.out.println("Distancias:");
		for (String v : d.keySet()) {
			System.out.println(v + " = " + d.get(v));
		}

	}

}
