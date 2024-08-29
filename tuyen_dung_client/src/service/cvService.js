import axios from "axios"

const getAllListCvByUserIdService = (data) => {
    return axios.get(`/api/get-all-cv-by-userId?limit=${data.limit}&offset=${data.offset}&userId=${data.userId}`)
}

export {
     getAllListCvByUserIdService
}