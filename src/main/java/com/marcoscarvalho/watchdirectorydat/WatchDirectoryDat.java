package com.marcoscarvalho.watchdirectorydat;

import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY;
import static java.nio.file.StandardWatchEventKinds.OVERFLOW;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.marcoscarvalho.watchdirectorydat.bo.FileBO;

public class WatchDirectoryDat {

	private static final Logger logger = LogManager.getLogger(WatchDirectoryDat.class);

	public final String folderIn = "data/in/";
	public final String folderOut = "data/out/";

	private final WatchService watcher;
	private final Map<WatchKey, Path> keys;

	public static void main(String[] args) {
		try {
			WatchDirectoryDat wdd = new WatchDirectoryDat();
			wdd.processEvents();

		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

	WatchDirectoryDat() throws IOException {
		Path dir = Paths.get(folderIn);
		this.watcher = FileSystems.getDefault().newWatchService();
		this.keys = new HashMap<WatchKey, Path>();

		WatchKey key = dir.register(watcher, ENTRY_CREATE, ENTRY_MODIFY);
		keys.put(key, dir);
	}

	/**
	 * Process all events for keys queued to the watcher
	 */
	void processEvents() {
		for (;;) {

			// wait for key to be signalled
			WatchKey key;
			try {
				key = watcher.take();
			} catch (InterruptedException x) {
				return;
			}

			for (WatchEvent<?> event : key.pollEvents()) {

				if (event.kind() == OVERFLOW) {
					continue;
				}

				final Path name = (Path) event.context();
				if (name.toString().endsWith(".dat") || name.toString().endsWith(".DAT")) {
					logger.info("Process file >> " + name);
					new FileBO(folderIn, folderOut, name.toString()).start();
				}
			}

			// reset key and remove from set if directory no longer accessible
			boolean valid = key.reset();
			if (!valid) {
				keys.remove(key);

				// all directories are inaccessible
				if (keys.isEmpty()) {
					break;
				}
			}
		}
	}

}