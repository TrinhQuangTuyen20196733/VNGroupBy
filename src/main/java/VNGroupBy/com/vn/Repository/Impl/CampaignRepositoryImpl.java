package VNGroupBy.com.vn.Repository.Impl;

import VNGroupBy.com.vn.DTO.request.ApiParameter;
import VNGroupBy.com.vn.Entity.Campaign;
import VNGroupBy.com.vn.Entity.Product;
import VNGroupBy.com.vn.Repository.CustomizedRepository.CustomCampaignRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CampaignRepositoryImpl implements CustomCampaignRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Campaign> getByFilter(ApiParameter apiParameter) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Campaign> query = criteriaBuilder.createQuery(Campaign.class);
        Root<Campaign> root = query.from(Campaign.class);
        List<Predicate> predicates = new ArrayList<>();
        List<Order> orders = new ArrayList<>();
        if (apiParameter.filter!=null) {
            if (apiParameter.filter.categoryCode != null) {
                predicates.add(criteriaBuilder.equal(root.get("product").get("category").get("code"), apiParameter.filter.categoryCode));
            }
            if (apiParameter.filter.orderBy!=null) {
                String field = apiParameter.filter.orderBy;
                if (apiParameter.filter.ascending == true) {
                    orders.add(criteriaBuilder.asc(root.get(field)));
                } else {
                    orders.add(criteriaBuilder.desc(root.get(field)));
                }
            }

        }

        if (!predicates.isEmpty()) {

            query.where(predicates.toArray(new Predicate[0]));
        }



        query.orderBy(orders);
        TypedQuery<Campaign> typedQuery = entityManager.createQuery(query);
        if (apiParameter.limit != 0) {
            typedQuery = typedQuery.setMaxResults(apiParameter.limit);

        }
        return typedQuery.getResultList();
    }
}
