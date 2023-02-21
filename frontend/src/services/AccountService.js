import axios from "./axios"

// get all the accounts
const getAll = () => {
    const request = axios.get("/accounts")
    return request.then(response => response.data.data)
}

// create a new account
const create = (newObject) => {
    const request = axios.post("/accounts", newObject)
    return request.then(response => response.data)
}

const AccountService = {
    getAll,
    create
}

export default AccountService