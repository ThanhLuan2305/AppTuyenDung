package com.doan.AppTuyenDung.Services;

import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.doan.AppTuyenDung.Repositories.DetailPostRepository;
import com.doan.AppTuyenDung.Repositories.PostRepositoriesQuery;
import com.doan.AppTuyenDung.Repositories.PostRepository;
import com.doan.AppTuyenDung.Repositories.SearchRepository;
import com.doan.AppTuyenDung.Repositories.criteria.FilterData;
import com.doan.AppTuyenDung.entity.DetailPost;
import com.doan.AppTuyenDung.entity.Post;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.springframework.util.StringUtils;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import com.doan.AppTuyenDung.DTO.DetailPostDTO;
import com.doan.AppTuyenDung.DTO.FilterCriteria;
import com.doan.AppTuyenDung.DTO.Response.PageResponse;
import com.doan.AppTuyenDung.DTO.Response.PostJobTypeCountDTO;
import com.doan.AppTuyenDung.DTO.Response.postDetailResponse;
import com.doan.AppTuyenDung.DTO.InfoPostDetailDto;
import java.util.*;
import java.util.regex.Matcher;
import static com.doan.AppTuyenDung.utils.AppConst.SEARCH_SPEC_OPERATOR;
import static com.doan.AppTuyenDung.utils.AppConst.SORT_BY;

@Service
public class postService {
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private DetailPostRepository detailPostRepository;

    @Autowired
    private EntityManager entityManager;
    @Autowired
    private PostRepositoriesQuery postRepositoriesQuery;

    @Autowired
    private SearchRepository searchRepository;
    // public PageResponse<?> advanceSearchByCriteria(int pageNo,int pageSize,String sortBy, Integer isHot,String... search)
    // {
    //     return searchRepository.advanceSearchDetailPost(pageNo, pageSize, sortBy,isHot, search);
    // }

    //amount post and get 
    public Page<PostJobTypeCountDTO> getPostJobTypeAndCountPost(Pageable pageable) {
        Page<Object[]> rawResults = postRepositoriesQuery.findPostJobTypeAndCountPost(pageable);

        List<PostJobTypeCountDTO> dtos = rawResults.stream()
            .map(result -> new PostJobTypeCountDTO(
                (String) result[0],          // categoryJobCode
                ((Number) result[1]).intValue(),
                (String) result[2],          // value
                (String) result[3],          // code
                (String) result[4]           // image
            ))
            .collect(Collectors.toList());
            int pageNumber = rawResults.getNumber(); // Current page number
            int pageSize = rawResults.getSize(); // Size of each page
            long totalElements = rawResults.getTotalElements(); // Total number of elements

            return new PageImpl<>(dtos, PageRequest.of(pageNumber, pageSize), totalElements);
    }

    // get Detail Post 
    public ResponseEntity<?> getPostDetailById(Integer id) {
        List<InfoPostDetailDto> postDetails = postRepositoriesQuery.findPostDetailById(id);

        InfoPostDetailDto postDetail = postDetails.stream().findFirst().orElse(null);

        if (postDetail != null) {
            return ResponseEntity.ok(postDetail); // Trả về DTO nếu tìm thấy
        } else {
            return ResponseEntity.status(HttpStatus.SC_BAD_REQUEST)
                                 .body("Không tìm thấy bài viết"); // Trả về thông báo lỗi nếu không tìm thấy
        }
    }

    // Filter post and detail post
    public Page<DetailPostDTO> getFilteredDetailPosts(String categoryJobCode, String addressCode, String search,
                                                      List<String> experienceJobCodes, List<String> categoryWorktypeCodes,
                                                      List<String> salaryJobCodes, List<String> categoryJoblevelCodes,
                                                      Integer isHot, Pageable pageable) {
        
        
        return postRepositoriesQuery.findFilteredPosts(categoryJobCode, 
                                                            addressCode, 
                                                        search, 
                                                       experienceJobCodes,
                                                     categoryWorktypeCodes, 
                                                     salaryJobCodes, 
                                                     categoryJoblevelCodes, 
                                                     isHot, 
                                                     pageable);
    }

}


    

