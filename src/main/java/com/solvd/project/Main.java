package com.solvd.project;

import com.solvd.project.model.Strawberry;
import com.solvd.project.model.Vehicles;
import com.solvd.project.model.Singleton;
import com.solvd.project.model.Washer;
import com.solvd.project.model.Watermelon;
import com.solvd.project.model.WeatherConditions;
import com.solvd.project.model.Witness;
import com.solvd.project.service.DriverService;
import com.solvd.project.service.PaymentsService;
import com.solvd.project.service.PolicyHolderService;
import com.solvd.project.service.PolicyService;
import com.solvd.project.service.VehicleService;
import com.solvd.project.service.XMLImportService;
import com.solvd.project.utils.SpecialWordCounter;
import com.solvd.project.interfaces.Product;
import com.solvd.project.interfaces.Storable;
import com.solvd.project.interfaces.StringTransformer;
import com.solvd.project.model.QueueSize;
import com.solvd.project.model.Adjuster;
import com.solvd.project.model.Apple;
import com.solvd.project.model.Potato;
import com.solvd.project.interfaces.Pricable;
import com.solvd.project.exceptions.PriceException;
import com.solvd.project.interfaces.Processable;
import com.solvd.project.interfaces.MathOperation;
import com.solvd.project.interfaces.MessagePrinter;
import com.solvd.project.model.ProcessingLine;
import com.solvd.project.model.Onion;
import com.solvd.project.model.Payments;
import com.solvd.project.model.Peeler;
import com.solvd.project.model.Policy;
import com.solvd.project.model.PolicyHolders;
import com.solvd.project.model.Cherry;
import com.solvd.project.model.Chiller;
import com.solvd.project.model.Claims;
import com.solvd.project.model.Drivers;
import com.solvd.project.model.Client;
import com.solvd.project.interfaces.Harvestable;
import com.solvd.project.exceptions.WeightCheckException;
import com.solvd.project.model.Carrot;
import com.solvd.project.model.Banana;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.solvd.project.Main;
import com.solvd.project.annotations.RunImmediately;
import com.solvd.project.dao.interfaces.GenericDAO;
import com.solvd.project.enums.ProcessingStage;
import com.solvd.project.enums.RipenessLevel;
import com.solvd.project.enums.StorageType;
import com.solvd.project.dao.PolicyHolderDAO;
import com.solvd.project.dao.VehicleDAO;
import com.solvd.project.dao.PolicyDAO;
import com.solvd.project.dao.WeatherConditionsDAO;
import com.solvd.project.dao.PaymentDAO;
import com.solvd.project.dao.DriverDAO;
import com.solvd.project.dao.ClaimDAO;
import com.solvd.project.dao.ClientDAO;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

public class Main implements Runnable {

        private final String threadName;

        public Main(String threadName) {
                this.threadName = threadName;
        }

        public static void main(String[] args) throws WeightCheckException, QueueSize, PriceException,
                        IllegalAccessException, InvocationTargetException {
                List<Product> farmProducts = new ArrayList<>();
                LinkedList<Pricable> prices = new LinkedList<>();
                Map<String, Harvestable> harvestMap = new HashMap<>();
                Set<Storable> storageSet = new HashSet<>();
                Stream<Storable> data = storageSet.stream();
                final Logger logger = LogManager.getLogger(Main.class);

                Thread t1 = new Thread(new Main("Worker-1"));
                Thread t2 = new Thread(new Main("Worker-2"));
                Thread t3 = new Thread(new Main("Worker-3"));

                t1.start();
                t2.start();
                t3.start();

                // ⬇️ Add products List
                farmProducts.add(new Watermelon(4.5, true, LocalDate.of(2025, 6, 25), 10, false, RipenessLevel.RIPE,
                                StorageType.REFRIGERATED, ProcessingStage.WASHED));
                farmProducts.add(new Apple(0.2, true, LocalDate.of(2025, 6, 8), 11, true, RipenessLevel.RIPE,
                                StorageType.REFRIGERATED, ProcessingStage.WASHED));
                farmProducts.add(new Banana(0.25, false, LocalDate.of(2026, 12, 14), 5, RipenessLevel.RIPE,
                                StorageType.REFRIGERATED, ProcessingStage.WASHED));
                farmProducts.add(new Cherry(0.5, false, LocalDate.of(2027, 4, 11), 8, RipenessLevel.RIPE,
                                StorageType.REFRIGERATED, ProcessingStage.WASHED));
                farmProducts.add(new Potato(1, true, LocalDate.of(2030, 7, 12), 9, RipenessLevel.RIPE,
                                StorageType.REFRIGERATED, ProcessingStage.WASHED));
                farmProducts.add(new Onion(1.2, true, LocalDate.of(2015, 8, 15), 10, false, RipenessLevel.RIPE,
                                StorageType.REFRIGERATED, ProcessingStage.WASHED));
                farmProducts.add(new Strawberry(0.3, false, LocalDate.of(2020, 4, 25), 10, true, RipenessLevel.RIPE,
                                StorageType.REFRIGERATED, ProcessingStage.WASHED));
                farmProducts.add(new Carrot(1.5, false, LocalDate.of(2018, 1, 3), 16, true, RipenessLevel.RIPE,
                                StorageType.REFRIGERATED, ProcessingStage.WASHED));

                // ⬇️ Add products LinkedList
                prices.add(new Apple(0.2, true, LocalDate.of(2025, 6, 8), 11, true, RipenessLevel.RIPE,
                                StorageType.REFRIGERATED, ProcessingStage.WASHED));
                prices.add(new Watermelon(4.5, true, LocalDate.of(2025, 6, 25), 10, false, RipenessLevel.RIPE,
                                StorageType.REFRIGERATED, ProcessingStage.WASHED));
                prices.add(new Carrot(1.5, false, LocalDate.of(2018, 1, 3), 16, true, RipenessLevel.RIPE,
                                StorageType.REFRIGERATED, ProcessingStage.WASHED));
                prices.add(new Onion(1.2, true, LocalDate.of(2015, 8, 15), 10, false, RipenessLevel.RIPE,
                                StorageType.REFRIGERATED, ProcessingStage.WASHED));
                prices.add(new Strawberry(0.3, false, LocalDate.of(2020, 4, 25), 10, true, RipenessLevel.RIPE,
                                StorageType.REFRIGERATED, ProcessingStage.WASHED));

                // ⬇️ Add products HashMap
                harvestMap.put("apple01", new Apple(0.2, true, LocalDate.of(2025, 6, 8), 11, true, RipenessLevel.RIPE,
                                StorageType.REFRIGERATED, ProcessingStage.WASHED));
                harvestMap.put("melon01",
                                new Watermelon(4.5, true, LocalDate.of(2025, 6, 25), 10, false, RipenessLevel.RIPE,
                                                StorageType.REFRIGERATED, ProcessingStage.WASHED));
                harvestMap.put("carrot01",
                                new Carrot(1.5, false, LocalDate.of(2018, 1, 3), 16, true, RipenessLevel.RIPE,
                                                StorageType.REFRIGERATED, ProcessingStage.WASHED));

                // ⬇️ Add products to the HashSet
                storageSet.add(new Apple(0.2, true, LocalDate.of(2025, 6, 8), 11, true, RipenessLevel.RIPE,
                                StorageType.REFRIGERATED, ProcessingStage.WASHED));
                storageSet.add(new Watermelon(4.5, true, LocalDate.of(2025, 6, 25), 10, false, RipenessLevel.RIPE,
                                StorageType.REFRIGERATED, ProcessingStage.WASHED));
                storageSet.add(new Carrot(1.5, false, LocalDate.of(2018, 1, 3), 16, true, RipenessLevel.RIPE,
                                StorageType.REFRIGERATED, ProcessingStage.WASHED));

                try {
                        SpecialWordCounter.analyzeFromResources("article.txt", "results.txt");
                } catch (IOException e) {
                        e.printStackTrace();
                }

                try {
                        // 1. Connect to the database
                        Connection conn = DriverManager.getConnection(
                                        "jdbc:sqlserver://localhost:1433;databaseName=InsuranceCompany;encrypt=true;trustServerCertificate=true",
                                        "Tabler12",
                                        "1110");

                        // 2. Initialize 8 DAOs
                        GenericDAO<PolicyHolders, Integer> holderDAO = new PolicyHolderDAO(conn);
                        GenericDAO<Policy, Integer> policyDAO = new PolicyDAO(conn);
                        GenericDAO<Vehicles, Integer> vehicleDAO = new VehicleDAO(conn);
                        GenericDAO<Drivers, Integer> driverDAO = new DriverDAO(conn);
                        GenericDAO<Claims, Integer> claimDAO = new ClaimDAO(conn);
                        GenericDAO<Payments, Integer> paymentsDAO = new PaymentDAO(conn);
                        GenericDAO<Client, Integer> clientDAO = new ClientDAO(conn);
                        GenericDAO<WeatherConditions, Integer> weatherDAO = new WeatherConditionsDAO(conn);

                        // 3. Initialize 5 Services + StAX Parser Service
                        PolicyHolderService holderService = new PolicyHolderService(holderDAO);
                        PolicyService policyService = new PolicyService(policyDAO);
                        VehicleService vehicleService = new VehicleService(vehicleDAO);
                        DriverService driverService = new DriverService(driverDAO);
                        PaymentsService paymentsService = new PaymentsService(paymentsDAO);
                        XMLImportService xmlService = new XMLImportService();

                        // 4. Load and process XML data
                        InputStream xmlStream = new FileInputStream("insurance_data.xml");

                        // 5. Use services
                        System.out.println("📋 All Policy Holders:");
                        List<PolicyHolders> holders = holderService.getAll();
                        for (PolicyHolders h : holders) {
                                System.out.println(" - " + h.getName() + " (" + h.getContact() + ")");
                        }

                        System.out.println("\n🚗 Adding new vehicle...");
                        Vehicles newVehicle = new Vehicles(0, "Tesla", "2025", "TESLA123VIN");
                        vehicleService.create(newVehicle);

                        System.out.println("\n✅ Vehicles added!");

                        System.out.println("📥 Importing Witnesses from XML...");
                        List<Witness> witnesses = xmlService.loadWitnesses(xmlStream);
                        for (Witness w : witnesses) {
                                System.out.println(" - Witness #" + w.getId() + ": " + w.getStatementSummary());

                        }

                        xmlStream = new FileInputStream("insurance_data.xml"); // Reset stream
                        System.out.println("\n📥 Importing Adjusters from XML...");
                        List<Adjuster> adjusters = xmlService.loadAdjusters(xmlStream);
                        for (Adjuster a : adjusters) {
                                System.out.println(" - Adjuster: " + a.getName() + ", Case: " + a.getAssignedCase());

                        }

                } catch (Exception e) {
                        e.printStackTrace();
                }

                // Generic classes usage
                Apple apple = new Apple(0.3, true, LocalDate.now(), 11, true, RipenessLevel.RIPE,
                                StorageType.REFRIGERATED,
                                ProcessingStage.WASHED);
                for (Method method : apple.getClass().getDeclaredMethods()) {
                        if (method.isAnnotationPresent(RunImmediately.class)) {
                                RunImmediately annotation = method.getAnnotation(RunImmediately.class);
                                for (int i = 0; i < annotation.times(); i++) {
                                        method.invoke(apple);
                                }
                        }
                }
                Processable washer = new Washer<>(apple);
                Processable peeler = new Peeler<>(apple);
                Processable chiller = new Chiller<>(apple);
                ProcessingLine<Processable> line = new ProcessingLine<>();

                line.enqueue(washer);
                line.enqueue(peeler);
                line.enqueue(chiller);

                line.processAll(); // Execute all preparation steps in order

                // Lambda to print a message
                MessagePrinter printer = msg -> logger.info(msg);
                printer.print("=== Farm Total Prices ===");
                // 📤 Get product at index 1 (Watermelon)
                Pricable secondProduct = prices.get(1);
                if (secondProduct instanceof Pricable pricable) {
                        logger.info("Product at index 1: %s — Total Price: $%.2f%n", pricable.getTotalPrice());
                }

                // ❌ Remove the first product (Apple)
                Pricable removed = prices.remove(0);
                logger.info("Removed product: " + prices.get(0));

                // 💰 Show total revenue of remaining products
                double totalRevenue = 0;
                for (Pricable p : prices) {
                        if (p instanceof Pricable pricable) {
                                totalRevenue += pricable.getTotalPrice();
                        }
                }

                // Lambda to transform a string (e.g., uppercase)
                StringTransformer toUpper = str -> str.toUpperCase();
                logger.info("Transformed: " + toUpper.transform("total revenue:") + totalRevenue);

                // 🚜 Iterate and harvest each item
                logger.info("=== Harvesting All Products ===");
                for (Map.Entry<String, Harvestable> entry : harvestMap.entrySet()) {
                        logger.info("Product ID: " + entry.getKey());
                        entry.getValue().harvest();
                }

                // 🌀 Iterate with forEach and print storage advice
                logger.info("Storage Recommendations:");
                data.forEach(
                                product -> logger.info(product.getClass().getSimpleName() + ": "
                                                + product.getStorageAdvice()));

                logger.info("===  Farm Inventory with Enum Insights ===");

                int ripeCount = 0;
                int unripeCount = 0;
                Map<RipenessLevel, Integer> ripenessStats = new EnumMap<>(RipenessLevel.class);
                Map<StorageType, Integer> storageStats = new EnumMap<>(StorageType.class);
                Map<ProcessingStage, Integer> stageStats = new EnumMap<>(ProcessingStage.class);

                double totalWeight = 0.0;

                for (Product p : farmProducts) {
                        logger.info(p.getName() + " - " + p.getWeight() + "kg - "
                                        + (p.isRipe() ? ripeCount++ : unripeCount++));
                        p.printDaysToSpoil();
                        totalWeight += p.getWeight(); // Accumulate weight

                        // If product supports enum tracking
                        if (p instanceof Banana banana) {
                                ripenessStats.merge(banana.ripenessLevel(), 1, Integer::sum);
                                storageStats.merge(banana.storageType(), 1, Integer::sum);
                                stageStats.merge(banana.stage(), 1, Integer::sum);
                        }

                }

                logger.info("\n=== Ripeness Summary ===");
                logger.info("✅ Ripe: " + ripeCount + " | ❌ Unripe: " + unripeCount);
                logger.info(String.format("Total weight: %.2f kg", totalWeight));

                logger.info("\n📊 Ripeness Breakdown:");
                ripenessStats.entrySet().stream()
                                .forEach(entry -> logger.info(entry.getKey() + ": " + entry.getValue()));

                logger.info("\n📦 Storage Type Breakdown:");
                storageStats.entrySet().stream()
                                .forEach(entry -> logger.info(entry.getKey() + ": " + entry.getValue()));

                logger.info("\n🔧 Processing Stage Breakdown:");
                stageStats.entrySet().stream()
                                .forEach(entry -> logger.info(entry.getKey() + ": " + entry.getValue()));

                MathOperation result = (a, b) -> (a * b);
                logger.info("Multiplied amount of riped and unriped: " + result.multiply(ripeCount, ripeCount));

                if (totalWeight < 5) {
                        throw new WeightCheckException("Weight can't be less than 5", new RuntimeException());
                }

        }

        @Override
        public void run() {
                Singleton singleton = Singleton.getInstance();
                singleton.printMessage("Hello from thread: " + threadName);
        }

}
