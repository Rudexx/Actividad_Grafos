package co.edu.unbosque.Controller;


import co.edu.unbosque.Model.Edge;
import co.edu.unbosque.Model.GrafoDirigido;
import co.edu.unbosque.Model.GrafoNoDirigido;
import co.edu.unbosque.Model.Node;
import co.edu.unbosque.View.View;

import java.util.ArrayList;

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
					try {
						String nombre = view.ingresoDeDatos("Ingrese el nombre que desea ponerle al nodo");
						grafo.addNode(new Node(nombre));
						view.mostrarInformacion("El nodo se anadio correctamente", "Exito", 1);
					} catch (Exception e) {
					} finally {
						run(opcion);
					}
					break;

				case "Agregar una relacion/arco":
					if(opcion==0){
						try {
							String[] nombreNodos = new String[grafo.getNodes().size()];

							if (nombreNodos.length > 0) {
								for (int i = 0; i < nombreNodos.length; i++) {
									nombreNodos[i] = grafo.returnNodeNames(i);
								}
								String nodo1 = view.mostrarListaDeOpciones(nombreNodos, "Elija alguno de los nodos a Continuacion");
								String nodo2 = view.mostrarListaDeOpciones(nombreNodos, "Elija un segundo nodo");
								int costo = Integer.parseInt(view.ingresoDeDatos("Ingrese el costo de transcurrir de un nodo a otro"));

								if (grafo.searchNode(nodo1).addEdge(new Edge(grafo.searchNode(nodo1), grafo.searchNode(nodo2), costo))&&
										grafo.searchNode(nodo2).addEdge(new Edge(grafo.searchNode(nodo2), grafo.searchNode(nodo1), costo))) {
									view.mostrarInformacion("El arco se anadio correctamente", "Exito", 1);
								} else {
									view.mostrarInformacion("Error: Ya existe un nodo con esas caracteristicas", "Fracaso", 0);
								}
							} else {
								view.mostrarInformacion("No existe ningun Nodo creado", "Error", 0);
								run(opcion);
							}
						} catch (Exception e2) {
						} finally {
							run(opcion);
						}
					}else{
						try {
							String[] nombreNodos = new String[grafo.getNodes().size()];

							if (nombreNodos.length > 0) {
								for (int i = 0; i < nombreNodos.length; i++) {
									nombreNodos[i] = grafo.returnNodeNames(i);
								}
								String nodo1 = view.mostrarListaDeOpciones(nombreNodos, "Elija alguno de los nodos a Continuacion");
								String nodo2 = view.mostrarListaDeOpciones(nombreNodos, "Elija un segundo nodo");
								int costo = Integer.parseInt(view.ingresoDeDatos("Ingrese el costo de transcurrir de un nodo a otro"));

								if (grafo.searchNode(nodo1).addEdge(new Edge(grafo.searchNode(nodo1), grafo.searchNode(nodo2), costo))) {
									view.mostrarInformacion("El nodo se añadio correctamente", "Exito", 1);
								} else {
									view.mostrarInformacion("Error: Ya existe un nodo con esas caracteristicas", "Fracaso", 0);
								}

							} else {
								view.mostrarInformacion("No existe ningun Nodo creado", "Error", 0);
								run(opcion);
							}
						} catch (Exception e2) {
						} finally {
							run(opcion);
						}
					}

					break;
				case "Eliminar Dato":
					if(grafo.getNodes().size() > 0) {
						String[] nombreNodos = new String[grafo.getNodes().size()];

						if (nombreNodos.length > 0) {
							for (int i = 0; i < nombreNodos.length; i++) {
								nombreNodos[i] = grafo.returnNodeNames(i);
							}
						}
						String nombre = view.mostrarListaDeOpciones(nombreNodos, "Elija el nodo que desea borrar");
						if (grafo.deleteNode(nombre)) {
							view.mostrarInformacion("El nodo y sus relaciones fueron eliminadas correctamente",
									"Exito", 1);
							run(opcion);
						} else {
							view.mostrarInformacion("El nodo no existe en el grafo!!!!",
									"Fracaso", 0);
							run(opcion);
						}
					}else {
						view.mostrarInformacion("No hay ningun nodo creado aun", "Error", 0);
						run(opcion);
					}

					break;
				case "eliminar Relacion/Arco":
					if(grafo.countEdges() > 0) {
						ArrayList<String> nombreEdges = new ArrayList<String>(grafo.countEdges());
						for (int i = 0; i < grafo.getNodes().size(); i++) {
							if (grafo.getNodes().get(i).getEdges() != null) {
								for (int j = 0; j < grafo.getNodes().get(i).getEdges().size(); j++) {
									nombreEdges.add(grafo.getEdges(i, j));
								}
							}
						}
						String[] ed = new String[nombreEdges.size()];

						for (int i = 0; i < nombreEdges.size(); i++) {
							ed[i] = nombreEdges.get(i);
						}


						String edge = view.mostrarListaDeOpciones(ed,
								"Elija el nodo que desea borrar");
						grafo.deleteOneEdge(edge);
						view.mostrarInformacion("La relacion se eliminó Correctamente", "Exito", 1);
						run(opcion);
					}else{
						view.mostrarInformacion("No hay ningun arco creado aun", "Error", 0);
						run(opcion);
					}

					break;
				case "Verificar si existe una ruta de un nodo a otro":
					if (!grafo.showPaths().equalsIgnoreCase("")) {
						try {
							String[] nombreNodos = new String[grafo.getNodes().size()];

							if (nombreNodos.length > 0) {
								for (int i = 0; i < nombreNodos.length; i++) {
									nombreNodos[i] = grafo.returnNodeNames(i);
								}
								String nodo1 = view.mostrarListaDeOpciones(nombreNodos,
										"Elija alguno de los nodos a Continuacion");
								String nodo2 = view.mostrarListaDeOpciones(nombreNodos,
										"Elija un segundo nodo");
								System.out.println(grafo.showPaths());

								if (grafo.matrizCaminos(grafo.searchNode(nodo1), grafo.searchNode(nodo2)) > 0.0) {
									view.mostrarInformacion("Si existe un camino hacia ese nodo",
											"Exito", 1);
								} else {
									view.mostrarInformacion("No Existe Un Camino Entre esos nodos",
											"Fracaso", 0);
								}

							} else {
								view.mostrarInformacion("No existe ningun Nodo creado",
										"Error", 0);
								run(opcion);
							}
						} catch (Exception e2) {
							e2.printStackTrace();
						} finally {
							run(opcion);
						}

						view.mostrarInformacion(grafo.showPaths(), "Muestreo", 1);
						run(opcion);
					}else{
						view.mostrarInformacion("No hay ningun nodo ni arco creado aun", "Error", 0);
						run(opcion);
					}
					break;
				case "Mostrar el camino menos costoso de un nodo hacia otro":
					if(opcion==0){
						String info = "Opcion valida solamente para grafo dirigido";
						view.mostrarInformacion(info, "Error", 0);
						run(opcion);
					}else{
						String info = grafo.algoritmoFloyd();

						for (int i = 0; i <grafo.getNodes().size() ; i++) {
							Node n = grafo.getNodes().get(i);
							info.replaceAll(String.valueOf(i+1), n.getCity());
						}

						view.mostrarInformacion(info, "Resultado" , 1);
						run(opcion);
					}

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
