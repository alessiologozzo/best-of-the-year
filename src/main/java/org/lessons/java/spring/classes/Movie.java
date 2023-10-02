package org.lessons.java.spring.classes;

import java.util.concurrent.atomic.AtomicInteger;

import org.lessons.java.spring.interfaces.Searchable;

public class Movie implements Searchable {
	private static final AtomicInteger counter = new AtomicInteger(1);
	private int id;
	private String title, path;

	public Movie(String title, String path) {
		this.id = counter.getAndIncrement();
		this.title = title;
		this.path = path;
	}

	public Movie(String title, String path, boolean autoIncrement) {
		if (autoIncrement)
			this.id = counter.getAndIncrement();
		else
			this.id = -1;

		this.title = title;
		this.path = path;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setPath(String path) {
		this.path = path;
	}

	@Override
	public int getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getPath() {
		return path;
	}
}
