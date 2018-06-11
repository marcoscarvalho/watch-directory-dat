package com.marcoscarvalho.watchdirectorydat.bo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.marcoscarvalho.watchdirectorydat.UtilFile.FileDatUtil;
import com.marcoscarvalho.watchdirectorydat.entity.Client;
import com.marcoscarvalho.watchdirectorydat.entity.Order;
import com.marcoscarvalho.watchdirectorydat.entity.Salesman;

public class FileBO extends Thread {

	private static final Logger logger = LogManager.getLogger(FileBO.class);

	private String folderIn;
	private String folderOut;
	private String name;

	public FileBO(String folderIn, String folderOut, String nameOfFile) {
		this.folderIn = folderIn;
		this.folderOut = folderOut;
		this.name = nameOfFile;
	}

	public void run() {
		List<String> list = FileDatUtil.readFile(folderIn + "/" + name);
		List<Client> clients = new ArrayList<Client>();
		List<Salesman> salesmans = new ArrayList<Salesman>();
		List<Order> orders = new ArrayList<Order>();

		for (String line : list) {
			if (line.startsWith("001")) {
				clients.add(new Client(line));

			} else if (line.startsWith("002")) {
				salesmans.add(new Salesman(line));

			} else if (line.startsWith("003")) {
				orders.add(new Order(line));

			} else {
				logger.info("Non-standard line >> " + line);
			}
		}

		Collections.sort(orders, new Comparator<Order>() {
			public int compare(Order o1, Order o2) {
				return o1.getTotalPrice().compareTo(o2.getTotalPrice());
			}
		});

		Map<String, BigDecimal> map = new HashMap<String, BigDecimal>();
		for (Order order : orders) {
			BigDecimal bd = BigDecimal.ZERO;
			if (map.containsKey(order.getSalesman())) {
				bd = map.get(order.getSalesman());
			}
			map.put(order.getSalesman(), bd.add(order.getTotalPrice()));
		}

		Map<String, BigDecimal> result = map.entrySet().stream()
				.sorted(Map.Entry.comparingByValue(Comparator.naturalOrder())).collect(Collectors.toMap(
						Map.Entry::getKey, Map.Entry::getValue, (oldValue, newValue) -> oldValue, LinkedHashMap::new));

		LinkedList<String> listFinal = new LinkedList<String>();
		listFinal.add("Quantidade de clientes no arquivo de entrada: " + clients.size());
		listFinal.add("Quantidade de vendedor no arquivo de entrada: " + salesmans.size());
		listFinal.add("ID da venda mais cara: " + orders.get(orders.size() - 1).getSaleId());
		listFinal.add("O pior vendedor: " + result.keySet().iterator().next());

		String finalFile = FileDatUtil.generateFile(folderOut + "/" + name, listFinal);
		logger.info("finalFile: " + finalFile + " >> " + listFinal);
	}

}
