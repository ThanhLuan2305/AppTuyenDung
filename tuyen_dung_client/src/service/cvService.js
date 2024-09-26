import axios from "axios"

const getAllListCvByUserIdService = (data) => {
    return axios.get(`http://localhost:8080/public/cv/get-all-cv-by-userId?limit=${data.limit}&offset=${data.offset}&userId=${data.userId}`)
}

const getDetailCvService = (id,roleCode) => {
    return axios.get(`/api/get-detail-cv-by-id?cvId=${id}&roleCode=${roleCode}`)
}

const getStatisticalCv = (data) => {
    return axios.get(`/api/get-statistical-cv?limit=${data.limit}&offset=${data.offset}&fromDate=${data.fromDate}&toDate=${data.toDate}&companyId=${data.companyId}`)
}
export {
     getAllListCvByUserIdService,
     getDetailCvService,
     getStatisticalCv
}