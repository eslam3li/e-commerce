package com.jets.ecommerce.dal.cfg;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.hibernate.SessionFactory;

import com.ibrahim.hibernate.transaction.TransactionManager;
import com.jets.ecommerce.dal.dao.CategoriesDao;
import com.jets.ecommerce.dal.dao.ProductItemsDao;
import com.jets.ecommerce.dal.dao.ProductsDao;
import com.jets.ecommerce.dal.dao.imp.CategoriesDaoImpl;
import com.jets.ecommerce.dal.dao.imp.ProductItemsDaoImpl;
import com.jets.ecommerce.dal.dao.imp.ProductsDaoImpl;
import com.jets.ecommerce.dal.entity.Category;
import com.jets.ecommerce.dal.entity.Product;
import com.jets.ecommerce.dal.entity.ProductItem;
import com.thedeanda.lorem.Lorem;
import com.thedeanda.lorem.LoremIpsum;


public class LoremData {

	private final static int CATEGORIES = 30;
	private final static int PRODUCTS = 100;

	private final TransactionManager transactionManager;
	private final Lorem lorem;
	private List<Category> categoryList = new ArrayList<>(CATEGORIES);
	private List<Product> productList = new ArrayList<>(PRODUCTS);
	private final CategoriesDao categoriesDao;
	private final ProductsDao productsDao;
	private final ProductItemsDao productItemsDao;

	private final static int[] images = { 1034843, 1043330, 1054777, 1065706, 1078973,
            1100784, 1100790, 1101928, 1102225, 1118715, 1139864, 1151440, 1152077, 115566,
            1204459, 1204464, 1212048, 1231485, 1250362, 130855, 1313064, 1352783, 135486,
            1359066, 1362558, 1377179, 1381562, 1395306, 1407130, 1413420, 1438409, 1451359,
            1453008, 1457801, 1493112, 1499481, 1532244, 1546333, 1591154, 1619655, 1639729,
            1660521, 1667850, 1670723, 1676126, 1676127, 1680646, 1697214, 1697215, 1697218,
            1697220, 1721937, 1744651, 177332, 1781621, 1793349, 179907, 179909, 1805597,
            187966, 207542, 2115260, 2115431, 227574, 234798, 236900, 237842, 255305,
            256273, 256868, 264709, 265856, 266621, 267278, 275033, 290043, 322674, 325867,
            326627, 346792, 346805, 346806, 348521, 350794, 351073, 354103, 355148, 356148,
            362685, 371095, 371102, 371153, 371723, 374601, 45055, 463467, 567983, 580707,
            633985, 63448, 65716, 69198, 701877, 735276, 744563, 753969, 814662, 842528,
            859895, 885218, 895849, 896094, 904350, 908629, 914196, 922567, 944031, 947885,
            949590, 954835, 984619, 988863, 989967, 994515, 994524 };

	private final static String[] colors = { "Red", "Orange", "Yellow", "Green", "Blue", "Purple", "Brown", "Silver",
			"Pink", "Black", "White" };

	public LoremData(SessionFactory sessionFactory) {
		transactionManager = new TransactionManager(sessionFactory);
		lorem = LoremIpsum.getInstance();
		categoriesDao = new CategoriesDaoImpl(sessionFactory);
		productsDao = new ProductsDaoImpl(sessionFactory);
		productItemsDao = new ProductItemsDaoImpl(sessionFactory);
	}

	private String getRandomImage() {
		Random random = new Random();
		int randomImage = random.nextInt(images.length);
		return "images/accessory_" + images[randomImage] + ".jpg";
	}

	public void insertAll() {
		insertCategories();
		insertProducts();
		insertProductItems();
	}

	public void insertCategories() {
		try {
			transactionManager.runInTransaction(() -> {
				List<Category> parentCategories = null;
				List<Category> categories;
				for (int i = 0; i < 3; i++) {
					categories = new ArrayList<>(10);
					for (int j = 0; j < 10; j++) {
						Category category = new Category();
						category.setName(lorem.getTitle(1));
						category.setPicture(getRandomImage());
						if (parentCategories != null) {
							Random random = new Random();
							int randomCategory = random.nextInt(parentCategories.size());
							category.setParentCategory(parentCategories.get(randomCategory));
						}
						categories.add(category);
						categoryList.add(category);
						categoriesDao.save(category);
					}
					parentCategories = categories;
				}
				return null;
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void insertProducts() {
		try {
			transactionManager.runInTransaction(() -> {
				for (int i = 0; i < PRODUCTS; i++) {
					Product product = new Product();
					product.setName(lorem.getNameMale());
					product.setDescription(lorem.getParagraphs(0, 5));
					String image = getRandomImage();
					product.setDisplayPicture(image);
					Random random = new Random();
					BigDecimal basePrice = BigDecimal.valueOf(random.nextFloat() * 2000);
					product.setBasePrice(basePrice);
					BigDecimal sellingPrice = basePrice.add(BigDecimal.valueOf(random.nextFloat() * 200).divide(BigDecimal.valueOf(100)));
					product.setSellingPrice(sellingPrice);
					product.setSale(BigDecimal.valueOf(random.nextInt(30)));
					int randomCategory = random.nextInt(CATEGORIES);
					product.setCategory(categoryList.get(randomCategory));
					List<String> pictures = new ArrayList<>(random.nextInt(5));
					for (int j = 0; j < 5; j++) {
						pictures.add(getRandomImage());
					}
					productList.add(product);
					productsDao.save(product);
				}
				return null;
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void insertProductItems() {
		try {
			transactionManager.runInTransaction(() -> {
				for (int i = 0; i < PRODUCTS; i++) {
					Random random = new Random();
					int numberOfProductItems = random.nextInt(colors.length);
					Product product = productList.get(i);
					for (int j = 0; j < numberOfProductItems; j++) {
						ProductItem productItem = new ProductItem();
						productItem.setQuantityInStock(random.nextInt(20));
						productItem.setColor(colors[j]);
						productItem.setProduct(product);
						productItemsDao.save(productItem);
					}
				}
				return null;
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
