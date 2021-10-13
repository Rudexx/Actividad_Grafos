package co.edu.unbosque.Controller;


import co.edu.unbosque.Model.Edge;
import co.edu.unbosque.Model.GrafoDirigido;
import co.edu.unbosque.Model.GrafoNoDirigido;
import co.edu.unbosque.Model.Node;
import co.edu.unbosque.View.View;

public class Controller {

    private View view;
    private GrafoDirigido grafo;
    private GrafoNoDirigido grafoD;


    public  Controller() {
        view = new View();
        grafo = new GrafoDirigido();
        grafoD = new GrafoNoDirigido();
        try {
			int opcion = view.preguntarTipo("Que tipo de Grafo desea Usar");
			run(opcion);
		}catch(Exception e){
        	System.exit(0);
		}

    }
    public void run(int opcion) {
    	try {
			String[] operaciones = {"Agregar un Dato" , "Agregar una relacion/arco" ,"Eliminar Dato" ,
					"eliminar Relacion/Arco", "Verificar si existe una ruta de un nodo a otro" ,
					"Mostrar el camino menos costoso de un nodo hacia otro", "Imprimir Grafo" };
			String eleccion = view.mostrarListaDeOpciones(operaciones, "Elija la operación que desea realizar");

			switch (eleccion) {
				case "Agregar un Dato":
					try{
						String nombre = view.ingresoDeDatos("Ingrese el nombre que desea ponerle al nodo");
						grafo.addNode(new Node(nombre));
					}catch (Exception e) {
					}finally {
						run(opcion);
					}
					break;
				case "Agregar una relacion/arco":
					try{
						String[] nombreNodos = new String[grafo.getNodes().size()];

						if(nombreNodos.length > 0){
							for (int i = 0; i < nombreNodos.length ; i++) {
								nombreNodos[i] = grafo.returnNodeNames(i);
							}
						String nodo1 = view.mostrarListaDeOpciones(nombreNodos, "Elija alguno de los nodos a Continuacion");
						String nodo2 = view.mostrarListaDeOpciones(nombreNodos, "Elija un segundo nodo");
						int costo = Integer.parseInt(view.ingresoDeDatos("Ingrese el costo de transcurrir de un nodo a otro"));

						if(grafo.searchNode(nodo1).addEdge(new Edge(grafo.searchNode(nodo1), grafo.searchNode(nodo2), costo))){
							view.mostrarInformacion("El nodo se añadio correctamente" , "Exito" , 1);
						}else{
							view.mostrarInformacion("Error: Ya existe un nodo con esas caracteristicas" , "Fracaso" , 0);
						}

						}else{
							view.mostrarInformacion("No existe ningun Nodo creado" , "Error" , 0);
							run(opcion);
						}
					}catch (Exception e) {
					}finally {
						run(opcion);
					}
					break;
				case "Eliminar Dato":

					break;
				case "eliminar Relacion/Arco":

					break;
				case "Verificar si existe una ruta de un nodo a otro":

					break;
				case "Mostrar el camino menos costoso de un nodo hacia otro":

					break;
				case "Imprimir Grafo":
					String info = grafo.toString();

					view.mostrarInformacion(info, "Información del grafo", 1);
					run(opcion);
					break;
			}
		}catch (Exception e){
    		System.exit(0);
		}
	}

}
