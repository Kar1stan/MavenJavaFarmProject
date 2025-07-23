package com.solvd.project.model;

import java.util.LinkedList;
import java.util.Queue;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.solvd.project.interfaces.Processable;

public class ProcessingLine<T extends Processable> {
    private Queue<T> queue;
    private static final Logger logger = LogManager.getLogger(ProcessingLine.class);

    public ProcessingLine() {
        queue = new LinkedList<>();
    }

    public void enqueue(T item) {
        queue.add(item);
        System.out.println("✅ Added to processing line: " + item.getClass().getSimpleName());
    }

    public void processNext() {
        if (!queue.isEmpty()) {
            T item = queue.poll(); // FIFO: remove head
            System.out.println("🔄 Processing: ");
            item.prepare();
        } else {
            System.out.println("🚫 Processing line is empty.");
        }
    }

    public void processAll() throws QueueSize {
        while (!queue.isEmpty()) {
            processNext();
        }
        if (queue.size() < 0) {
            throw new QueueSize("The list is to small for processing", new Exception());
        }
    }
}
