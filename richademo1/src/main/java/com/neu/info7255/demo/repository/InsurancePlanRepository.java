package com.neu.info7255.demo.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.Id;

import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.neu.info7255.demo.InsurancePlan;


public class InsurancePlanRepository implements CrudRepository<InsurancePlan, String> {
	
	@Id
	private Long id = 5l;
	public static final String PLANS_KEY = "plans";
	private HashOperations<String, String, InsurancePlan> hashOps = null;
	
	public InsurancePlanRepository(RedisTemplate<String, InsurancePlan> redisTemplate) {
		this.hashOps = redisTemplate.opsForHash();
		
	}
	
	public InsurancePlanRepository() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public long count() {
		return hashOps.keys(PLANS_KEY).size();
	}

	@Override
	public void delete(String planType) {
		hashOps.delete(PLANS_KEY, planType);
	}

	@Override
	public void delete(InsurancePlan plan) {
		hashOps.delete(PLANS_KEY,  plan.getObjectId());
	}

	@Override
	public void delete(Iterable<? extends InsurancePlan> plans) {
		for (InsurancePlan p : plans) {
			delete(p);
		}
	}

	@Override
	public void deleteAll() {
		Set<String> planTypes = hashOps.keys(PLANS_KEY);
		for (String planType : planTypes) {
			delete(planType);
		}

	}

	@Override
	public boolean exists(String planType) {
		return hashOps.hasKey(PLANS_KEY,  planType);
	}

	@Override
	public Iterable<InsurancePlan> findAll() {
		return hashOps.values(PLANS_KEY);
	}

	@Override
	public Iterable<InsurancePlan> findAll(Iterable<String> planTypes) {
		return hashOps.multiGet(PLANS_KEY, convertIterableToList(planTypes));
	}

	@Override
	public InsurancePlan findOne(String planTypes) {
		System.out.println("fetching plantype");
		return hashOps.get(PLANS_KEY, planTypes);
	}

	@Override
	public <S extends InsurancePlan> S save(S plan) {
		hashOps.put(PLANS_KEY, plan.getPlanType(), plan);

		return plan;
	}

	@Override
	public <S extends InsurancePlan> Iterable<S> save(Iterable<S> plans) {
		List<S> result = new ArrayList();

		for(S entity : plans) {
			save(entity);
			result.add(entity);
		}

		return result;
	}

	private <T> List<T> convertIterableToList(Iterable<T> iterable) {
        List<T> list = new ArrayList<>();
        for (T object : iterable) {
            list.add(object);
        }
        return list;
    }

	
	

}
