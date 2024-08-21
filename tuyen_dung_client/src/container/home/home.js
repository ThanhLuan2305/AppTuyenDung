import React, { useState, useEffect } from "react";
import { Link } from "react-router-dom";
import styled from "styled-components";
import Categories from "../../components/home/Categories";
import FeaturesJob from "../../components/home/FeaturesJobs";
// import { getListPostService } from "../../service/userService";
import axios from "axios";
const Home = () => {
  const [dataFeature, setDataFeature] = useState([]);
  const [dataHot, setDataHot] = useState([]);
  const buildParams = (params) => {
    // Loại bỏ các tham số có giá trị rỗng hoặc undefined
    return Object.fromEntries(
      Object.entries(params).filter(([_, v]) => v !== "" && v !== undefined)
    );
  };
  let loadPost = async (limit, offset) => {
    const paramsFeature = buildParams({
      limit: 5,
      offset: 0,
      categoryJobCode: "",
      addressCode: "",
      salaryJobCode: "",
      categoryJoblevelCode: "",
      categoryWorktypeCode: "",
      experienceJobCode: "",
      isHot: 0,
      search: "",
    });
    axios
      .get("http://localhost:8080/public/get-filter-post", {
        params: paramsFeature,
      })
      .then((responseFeature) => {
        setDataFeature(responseFeature.data);
      })
      .catch((error) => {
        console.error("Error fetching feature data", error);
      });
      
    const paramsHot = buildParams({
      limit: 5,
      offset: 0,
      categoryJobCode: "",
      addressCode: "",
      salaryJobCode: "",
      categoryJoblevelCode: "",
      categoryWorktypeCode: "",
      experienceJobCode: "",
      isHot: 1,
      search: "",
    });
    
    axios
      .get("http://localhost:8080/public/get-filter-post", { params: paramsHot })
      .then((responseHot) => {
        setDataHot(responseHot.data);
      })
      .catch((error) => {
        console.error("Error fetching hot data", error);
      });
  };
  useEffect(() => {
    let fetchPost = async () => {
      await loadPost(5, 0);
    };
    fetchPost();
  }, []);

  const H1 = styled.h1`
    color: darkblue;
    width: 600px;
    font-size: 2rem; /* Kích thước chữ cơ bản */
    margin: 0;

    @media (max-width: 768px) {
      width: 275px;
      font-size: 1.5rem; /* Kích thước chữ nhỏ hơn cho màn hình nhỏ */
      text-align: left; /* Căn giữa trên màn hình nhỏ */
    }

    @media (max-width: 576px) {
      font-size: 1.2rem; /* Kích thước chữ nhỏ hơn nữa cho màn hình rất nhỏ */
    }
  `;
  return (
    <>
      {/* <div id="preloader-active">
        <div class="preloader d-flex align-items-center justify-content-center">
            <div class="preloader-inner position-relative">
                <div class="preloader-circle"></div>
                <div class="preloader-img pere-text">
                    <img src="assets/img/logo/logo.png" alt="" />
                </div>
            </div>
        </div>
    </div> */}
      {/* <!-- Preloader Start --> */}
      <main>
        {/* <!-- slider Area Start--> */}
        <div class="slider-area ">
          {/* <!-- Mobile Menu --> */}
          <div class="slider-active">
            <div
              class="single-slider slider-height d-flex align-items-center"
              style={{
                backgroundImage: `url("./assets/img/hero/hero1.jpg")`,
              }}
            >
              <div class="container">
                <div class="row">
                  <div class="col-xl-6 col-lg-5 col-md-10">
                    <div class="hero__caption ">
                      <H1>Hãy tìm công việc yêu thích của bản thân</H1>
                    </div>
                  </div>
                </div>
                {/* <!-- Search Box --> */}
              </div>
            </div>
          </div>
        </div>
        {/* <!-- slider Area End-->
        <!-- Our Services Start --> */}
        <div class="our-services section-pad-t30">
          <div class="container">
            {/* <!-- Section Tittle --> */}
            <div class="row">
              <div class="col-lg-12">
                <div class="section-tittle text-center">
                  <p style={{ color: "red" }}>Lĩnh vực công việc nổi bật</p>
                  <h1>Danh mục nghề nghiệp </h1>
                </div>
              </div>
            </div>
            <Categories />

            {/* <!-- More Btn -->
                <!-- Section Button --> */}
          </div>
        </div>
        {/* <!-- Our Services End -->
        <!-- Online CV Area Start --> */}
        <div
          class="online-cv cv-bg section-overly pt-90 pb-120"
          style={{
            backgroundImage: `url("./assets/img/gallery/cv_bg.jpg")`,
          }}
        >
          <div class="container">
            <div class="row justify-content-center">
              <div class="col-xl-10">
                <div class="cv-caption text-center">
                  <p class="pera1">Nhiều công việc đang chờ bạn</p>
                  <p class="pera2"> Bạn đã sẵn sàng tìm việc chưa ?</p>
                  <Link to="/job" class="btn btn-primary">
                    Tìm việc ngay
                  </Link>
                </div>
              </div>
            </div>
          </div>
        </div>
        {/* <!-- Online CV Area End-->
        <!-- Featured_job_start --> */}
        <section class="featured-job-area feature-padding">
          <div class="container">
            {/* <!-- Section Tittle --> */}
            <div class="row">
              <div class="col-lg-12">
                <div class="section-tittle text-center">
                  <h2>Công việc nổi bật</h2>
                </div>
              </div>
            </div>
            <FeaturesJob dataFeature={dataHot} />
          </div>
        </section>
        <section class="featured-job-area feature-padding">
          <div class="container">
            {/* <!-- Section Tittle --> */}
            <div class="row">
              <div class="col-lg-12">
                <div class="section-tittle text-center">
                  <h2>Công việc mới đăng</h2>
                </div>
              </div>
            </div>
            <FeaturesJob dataFeature={dataFeature} />
          </div>
        </section>

        {/* <!-- Featured_job_end -->
        <!-- How  Apply Process Start--> */}
        <div
          class="apply-process-area apply-bg pt-150 pb-150"
          style={{
            backgroundImage: `url("assets/img/gallery/how-applybg.png")`,
          }}
        >
          <div class="container">
            {/* <!-- Section Tittle --> */}
            <div class="row">
              <div class="col-lg-12">
                <div class="section-tittle white-text text-center">
                  <span>Quy trình tìm việc</span>
                  <h2> Thực hiện như thế nào ?</h2>
                </div>
              </div>
            </div>
            {/* <!-- Apply Process Caption --> */}
            <div class="row">
              <div class="col-lg-3 col-md-6">
                <div class="single-process text-center mb-30">
                  <div class="process-ion">
                    <img
                      src="   https://cdn-icons-png.flaticon.com/512/3850/3850285.png "
                      width="125"
                      height="100"
                      alt=""
                      title=""
                      class="img-small"
                    />
                  </div>
                  <div class="process-cap">
                    <h5>1. Tìm kiếm công việc</h5>
                  </div>
                </div>
              </div>
              <div class="col-lg-3 col-md-6">
                <div class="single-process text-center mb-30">
                  <div class="process-ion">
                    <img
                      src="   https://cdn-icons-png.flaticon.com/512/15188/15188916.png "
                      width="125"
                      height="100"
                      alt=""
                      title=""
                      class="img-small"
                    />
                  </div>
                  <div class="process-cap">
                    <h5>2. Ứng tuyển công việc</h5>
                  </div>
                </div>
              </div>
              <div class="col-lg-3 col-md-6">
                <div class="single-process text-center mb-30">
                  <div class="process-ion">
                    <img
                      src="   https://cdn-icons-png.flaticon.com/512/3135/3135682.png "
                      width="125"
                      height="100"
                      alt=""
                      title=""
                      class="img-small"
                    />
                  </div>
                  <div class="process-cap">
                    <h5>3. Trao đổi phỏng vấn</h5>
                  </div>
                </div>
              </div>
              <div class="col-lg-3 col-md-6">
                <div class="single-process text-center mb-30">
                  <div class="process-ion">
                    <img
                      src="   https://cdn-icons-png.flaticon.com/512/2303/2303913.png "
                      width="125"
                      height="100"
                      alt=""
                      title=""
                      class="img-small"
                    />
                  </div>
                  <div class="process-cap">
                    <h5>4. Lời đề nghị và nhận công việc</h5>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </main>
    </>
  );
};

export default Home;
