import axios from "./axios"

// get all the customers
const getAll = () => {
    const request = axios.get("/customers")
    return request.then(response => response.data.data)
}

//  get a specific customer
const getCustomer = (id) => {
    const request = axios.get(`${"/customers"}/${id}`)
    return request.then(response => response.data)
}

// create a new customer
const create = (newObject) => {
    const request = axios.post("/customers", newObject)
    return request.then(response => response.data)
}

const CustomerService = {
    getAll,
    getCustomer,
    create
}

export default CustomerService