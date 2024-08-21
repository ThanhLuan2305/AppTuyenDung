import axios from "../axios";

const getListPostService = (data) => {
  if (!data?.search) {
    data.search = "";
  }
  if (data.isHot === 1) {
    return axios.get(
      `/public/get-filter-post?limit=${data.limit}&offset=${data.offset}&categoryJobCode=${data.categoryJobCode}&addressCode=${data.addressCode}&salaryJobCode=${data.salaryJobCode}&categoryJoblevelCode=${data.categoryJoblevelCode}&categoryWorktypeCode=${data.categoryWorktypeCode}&experienceJobCode=${data.experienceJobCode}&isHot=${data.isHot}&search=${data.search}`
    );
  }
  return axios.get(
    `/public/get-filter-post?limit=${data.limit}&offset=${data.offset}&categoryJobCode=${data.categoryJobCode}&addressCode=${data.addressCode}&salaryJobCode=${data.salaryJobCode}&categoryJoblevelCode=${data.categoryJoblevelCode}&categoryWorktypeCode=${data.categoryWorktypeCode}&experienceJobCode=${data.experienceJobCode}&search=${data.search}`
  );
};
const getListJobTypeAndCountPost = (data) => {};

//===============ALL CODE========================//
const getAllCodeService = (type) => {
  return axios.get(`/public/get-all-code?type=${type}`);
};

const getDetailPostByIdService = (id) => {
  return axios.get(`/public/get-detail-post-by-id?id=${id}`);
};

const handleLoginService = (data) => {
  return axios.post(`http://localhost:8080/auth/login`, data);
};

const createNewUser = (data) => {
  return axios.post(`http://localhost:8080/auth/register`, data)
}



export {
  getListPostService,
  getListJobTypeAndCountPost,
  getAllCodeService,
  getDetailPostByIdService,
  handleLoginService,
  createNewUser
};
