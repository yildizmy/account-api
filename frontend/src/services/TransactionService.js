import axios from "./axios"

// get all the transactions
const getAllByCustomerId = (id) => {
    const request = axios.get("/transactions/" + id)
    return request.then(response => response.data.data)
}

const TransactionService = {
    getAllByCustomerId
}

export default TransactionService