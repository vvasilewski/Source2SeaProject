package com.example.products.dao;

import com.example.products.entity.PriceSum;
import com.example.products.entity.Product_x;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;


@Repository
public class ProductDAOHibernateImpl implements ProductDAO{


    private EntityManager entityManager;


    @Autowired
    public ProductDAOHibernateImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    @Override
    public List<Product_x> findAll() {

        Session currentSession = entityManager.unwrap(Session.class);
        Query<Product_x> productList = currentSession.createQuery("from Product_x", Product_x.class);

        return productList.getResultList();
    }

    @Override
    public Product_x findById(int productId) {

        Session currentSession = entityManager.unwrap(Session.class);
        Product_x theProduct = currentSession.get(Product_x.class, productId);

        return theProduct;
    }


    public PriceSum getPriceByIds(List<Integer> idList){

        Session currentSession = entityManager.unwrap(Session.class);

        Function<List<Integer>, Map<Integer, List<Product_x>>> takingProductsFunction = a -> {
            Map<Integer, List<Product_x>> listMap= new HashMap<>();
            a.forEach(id -> {
                Query theQuery = currentSession.createQuery("from Product_x where id in (:theId)");
                theQuery.setParameter("theId", id);
                Product_x theProductX = (Product_x) theQuery.getSingleResult();
                listMap.computeIfAbsent(theProductX.getId(), k ->new ArrayList<>()).add(theProductX);
            }
            );
            return listMap;
        };

        Predicate<List<Product_x>> properToDiscountPredicate = productXList -> {
            if (productXList.size()>1 && productXList.get(0).getDiscount() != null){
                String[] splitValues = productXList.get(0).getDiscount().split(",");
                if (splitValues.length > 1){
                    Integer numberOfItems = Integer.valueOf(splitValues[0]);
                    Integer discountValue = Integer.valueOf(splitValues[1]);
                    if (numberOfItems!=null && discountValue!=null){
                        if(productXList.size()/numberOfItems>0){
                            return true;
                        }
                    }
                }
            }
        return false;
        };

        Function<List<Product_x>, BigDecimal> discountAction = list -> {

            BigDecimal calcOfOneTypeProduct= new BigDecimal(0);
            if(properToDiscountPredicate.test(list)){
                String discountInformation = list.get(0).getDiscount();
                String[] split = discountInformation.split(",");
                if(split[0]!=null && split[1]!=null){
                    Integer numberOfItems = Integer.valueOf(split[0]);
                    Integer discountValue = Integer.valueOf(split[1]);
                    Integer numberOfDiscounts = list.size()/numberOfItems;
                    BigDecimal valueToChange = list.stream().map(product -> product.getUnitPrice())
                            .reduce(BigDecimal.ZERO, BigDecimal::add);
                    calcOfOneTypeProduct = valueToChange
                            .subtract((BigDecimal.valueOf(numberOfDiscounts))
                                    .multiply(BigDecimal.valueOf(discountValue)));
                    return calcOfOneTypeProduct;
                }
            } else if (list.size()>0){
                calcOfOneTypeProduct = list.stream()
                        .map(product -> product.getUnitPrice())
                        .reduce(BigDecimal.ZERO, BigDecimal::add);
                return calcOfOneTypeProduct;

            }
            return calcOfOneTypeProduct;
        };



        //get products from db
        Map<Integer, List<Product_x>> allTheProducts = takingProductsFunction.apply(idList);

        //calculate price including discounts
        BigDecimal sumOfCalculation = allTheProducts.keySet().stream()
                .map(value -> discountAction.apply(allTheProducts.get(value)))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        PriceSum thePriceSum = new PriceSum(sumOfCalculation);
        return thePriceSum;

    }




    @Override
    public void save(Product_x theProductX) {

    }

    @Override
    public void deleteById(int productId) {

    }
}
