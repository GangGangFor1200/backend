//package ganggang3.gang.Repository;
//
//import ganggang3.gang.domain.Category;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//
//import java.util.List;
//
//public interface JpaRepository extends org.springframework.data.jpa.repository.JpaRepository<Category, Long> {
//
//    List<Category> findByName(String name);
//
//    @Query("select m  from Place m where m.category.id= :categoryid and m.city.id= :cityid")
//    List<Category> findByCategoryIdAndCityId(@Param("categoryid") String categoryid,
//                                             @Param("cityid")String cityId);
//}
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
