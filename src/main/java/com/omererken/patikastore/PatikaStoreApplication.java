package com.omererken.patikastore;

import com.omererken.patikastore.model.Brand;
import com.omererken.patikastore.model.Category;
import com.omererken.patikastore.model.Mobile;
import com.omererken.patikastore.model.Notebook;
import com.omererken.patikastore.repository.BrandRepository;
import com.omererken.patikastore.repository.CategoryRepository;
import com.omererken.patikastore.repository.MobileRepository;
import com.omererken.patikastore.repository.NotebookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

@SpringBootApplication
public class PatikaStoreApplication implements CommandLineRunner {

    private static Logger LOG = LoggerFactory
            .getLogger(PatikaStoreApplication.class);

    private static BrandRepository brandRepository;
    private static CategoryRepository categoryRepository;
    private static MobileRepository mobileRepository;
    private static NotebookRepository notebookRepository;

    public PatikaStoreApplication(BrandRepository brandRepository, CategoryRepository categoryRepository,
                                  MobileRepository mobileRepository, NotebookRepository notebookRepository) {
        this.brandRepository = brandRepository;
        this.categoryRepository = categoryRepository;
        this.mobileRepository = mobileRepository;
        this.notebookRepository = notebookRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(PatikaStoreApplication.class, args);
        addDefaultBrands();
        addDefaultCategories();
        consoleOperations();
    }

    private static void consoleOperations() {
        System.out.println("1=>List Product");
        System.out.println("2=>Add Product");
        System.out.println("3=>Delete Product");
        System.out.println("4=>Add Category");

        Scanner input = new Scanner(System.in);
        int choose = input.nextInt();

        switch (choose) {
            case 1:
                getProductListOperations(input);
                consoleOperations();
            case 2:
                addProductOperations(input);
                consoleOperations();
            case 3:
                deleteProductOperations(input);
                consoleOperations();
            case 4:
                addCategoryOperations(input);
                consoleOperations();
        }
    }

    private static void addProductOperations(Scanner input) {
        List<Category> categoryList = getCategoryListOrderByNameAsc();
        AtomicInteger i = new AtomicInteger(1);
        categoryList.forEach(category -> System.out.println(i.getAndIncrement() + "=>" + category.getName()));
        System.out.println("Select Category");
        int category = input.nextInt();
        if (category == 1) {
            Mobile mobile = new Mobile();
            AtomicInteger index = new AtomicInteger(1);
            getBrandListOrderByNameAsc().forEach(brand -> System.out.println(index.getAndIncrement() + "=>" + brand.getName()));
            System.out.println("Select Brand:");
            int indexOfBrandList;
            indexOfBrandList = input.nextInt();
            mobile.setBrand(getBrandListOrderByNameAsc().get(indexOfBrandList - 1));
            System.out.print("name = ");
            input.nextLine();
            String name = input.nextLine();
            mobile.setName(name);
            System.out.println("Price:");
            double price = input.nextDouble();
            mobile.setPrice(price);
            System.out.println("Discount Rate:");
            int discountRate = input.nextInt();
            mobile.setDiscountRate(discountRate);
            System.out.println("Amount of stock:");
            int amountOfStock = input.nextInt();
            mobile.setAmountOfStock(amountOfStock);
            System.out.println("Screen size:");
            double screenSize = input.nextDouble();
            mobile.setScreenSize(screenSize);
            System.out.println("Memory:");
            int memory = input.nextInt();
            mobile.setMemory(memory);
            System.out.println("Storage:");
            int storage = input.nextInt();
            mobile.setStorage(storage);
            System.out.println("Color:");
            input.nextLine();
            String color = input.nextLine();
            mobile.setColor(color);
            System.out.println("Battery");
            int battery = input.nextInt();
            mobile.setBatteryPower(battery);
            addMobile(mobile);
        }
        if (category == 2) {
            Notebook notebook = new Notebook();
            AtomicInteger index = new AtomicInteger(1);
            getBrandListOrderByNameAsc().forEach(brand -> System.out.println(index.getAndIncrement() + "=>" + brand.getName()));
            System.out.println("Select Brand:");
            int indexOfBrandList;
            indexOfBrandList = input.nextInt();
            notebook.setBrand(getBrandListOrderByNameAsc().get(indexOfBrandList - 1));
            System.out.print("name = ");
            input.nextLine();
            String name = input.nextLine();
            notebook.setName(name);
            System.out.println("Price:");
            double price = input.nextDouble();
            notebook.setPrice(price);
            System.out.println("Discount Rate:");
            int discountRate = input.nextInt();
            notebook.setDiscountRate(discountRate);
            System.out.println("Amount of stock:");
            int amountOfStock = input.nextInt();
            notebook.setAmountOfStock(amountOfStock);
            System.out.println("Screen size:");
            double screenSize = input.nextDouble();
            notebook.setScreenSize(screenSize);
            System.out.println("Memory:");
            int memory = input.nextInt();
            notebook.setMemory(memory);
            System.out.println("Storage:");
            int storage = input.nextInt();
            notebook.setStorage(storage);
            addNotebook(notebook);
        }
    }

    private static void getProductListOperations(Scanner input) {
        System.out.println("1=>All");
        System.out.println("2=>By Category");
        System.out.println("3=>By Id");
        System.out.println("4=>By Name");
        int subChoose = input.nextInt();

        if (subChoose == 1) {
            getProductList();
        } else if (subChoose == 2) {
            List<Category> categoryList = getCategoryListOrderByNameAsc();
            AtomicInteger i = new AtomicInteger(1);
            categoryList.forEach(category -> System.out.println(i.getAndIncrement() + "=>" + category.getName()));
            System.out.println("Select Category");
            int category = input.nextInt();

            if (category == 1)
                getMobileList();
            else if (category == 2)
                getNotebookList();

        } else if (subChoose == 3) {
            System.out.println("Enter Product Id");
            int productId = input.nextInt();
            getProductList();
        } else if (subChoose == 4) {
            System.out.println("Enter Product Name");
            input.nextLine();
            String productName = input.nextLine();
            getProductList();
        }
    }

    private static void deleteProductOperations(Scanner input) {
        getProductList();
        System.out.println("Enter the id of the product you want to delete");
        int productId = input.nextInt();
        deleteProduct(productId);
    }

    private static void getProductList() {
        List<Mobile> mobileList = getMobileList();
        System.out.println("---------------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("%5s%10s%10s%10s%15s%15s%10s%10s%15s%10s%10s", "ID", "NAME", "BRAND", "BATTERY", "AMOUNT STOCK", "DİSCOUNT RATE", "MEMORY", "PRİCE", "SCREEN SİZE", "COLOR", "STORAGE");
        System.out.println();
        System.out.println("---------------------------------------------------------------------------------------------------------------------------------------");
        mobileList.forEach(mobile -> System.out.printf("%5s%10s%11s%7s%11s%14s%15s%13s%12s%10s%10s",
                "|" + mobile.getId() + "|", "|" + mobile.getName() + "|", "|" + mobile.getBrand().getName() + "|", "|" + mobile.getBatteryPower() + "|", "|" + mobile.getAmountOfStock() + "|", "|" + mobile.getDiscountRate() + "|",
                "|" + mobile.getMemory() + "|", "|" + mobile.getPrice() + "|", "|" + mobile.getScreenSize() + "|", "|" + mobile.getColor() + "|", "|" + mobile.getStorage() + "|").println());

        List<Notebook> notebookList = getNotebookList();
        notebookList.forEach(notebook -> System.out.format("%10d%30s%30s%5d%3d%5d%10f%10f%5d",
                notebook.getId(), notebook.getName(), notebook.getBrand() != null ? notebook.getBrand().getName() : null, notebook.getAmountOfStock(), notebook.getDiscountRate(),
                notebook.getMemory(), notebook.getPrice(), notebook.getScreenSize(), notebook.getStorage()).println());
    }

    private static void addCategoryOperations(Scanner input) {
        System.out.print("category name = ");
        input.nextLine();
        String categoryName = input.nextLine();
        Category category = Category.builder().name(categoryName).build();
        addCategory(category);
    }

    private static void addDefaultBrands() {
        List<Brand> brandList = new ArrayList<>();
        brandList.add(Brand.builder().name("Samsung").build());
        brandList.add(Brand.builder().name("Lenovo").build());
        brandList.add(Brand.builder().name("Apple").build());
        brandList.add(Brand.builder().name("Huawei").build());
        brandList.add(Brand.builder().name("Casper").build());
        brandList.add(Brand.builder().name("Asus").build());
        brandList.add(Brand.builder().name("HP").build());
        brandList.add(Brand.builder().name("Xiaomi").build());
        brandList.add(Brand.builder().name("Monster").build());

        brandRepository.saveAll(brandList);
    }

    private static List<Brand> getBrandListOrderByNameAsc() {
        return brandRepository.findAll(Sort.by("name").ascending());
    }

    private static void addDefaultCategories() {
        List<Category> categoryList = new ArrayList<>();
        categoryList.add(Category.builder().name("Mobile").build());
        categoryList.add(Category.builder().name("Notebook").build());

        categoryRepository.saveAll(categoryList);
    }

    private static List<Category> getCategoryListOrderByNameAsc() {
        return categoryRepository.findAll(Sort.by("name").ascending());
    }

    private static void addCategory(Category category) {
        categoryRepository.save(category);
    }

    private static List<Mobile> getMobileList() {
        return mobileRepository.findAll();
    }

    private static Mobile getMobileById(long id) {
        return mobileRepository.findById(id).get();
    }

    private static Notebook getNotebookById(long id) {
        return notebookRepository.findById(id).get();
    }

    private static List<Mobile> getMobileByName(String name) {
        return mobileRepository.findAllByNameContainsIgnoreCase(name);
    }

    private static List<Notebook> getNotebookByName(String name) {
        return notebookRepository.findAllByNameContainsIgnoreCase(name);
    }

    private static List<Notebook> getNotebookList() {
        return notebookRepository.findAll();
    }

    private static void addMobile(Mobile mobile) {
        mobileRepository.save(mobile);
    }

    private static void addNotebook(Notebook notebook) {
        notebookRepository.save(notebook);
    }

    private static void deleteProduct(long id) {
        Optional<Mobile> mobile = mobileRepository.findById(id);
        if (mobile.isPresent()) {
            mobileRepository.deleteById(id);
        } else {
            notebookRepository.deleteById(id);

        }

    }

    @Override
    public void run(String... args) throws Exception {

    }
}
