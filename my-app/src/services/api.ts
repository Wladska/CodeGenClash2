import axios from 'axios';

const api = axios.create({
  baseURL: 'http://localhost:8080/api', // Adjust the URL if necessary
});

export const login = async (credentials: { username: string, password: string }) => {
  const response = await api.post('/auth/login', credentials);
  return response.data;
};

export const fetchEmployees = async () => {
  const response = await api.get('/employees');
  return response.data;
};

export const fetchEmployeeById = async (id: number) => {
  const response = await api.get(`/employees/${id}`);
  return response.data;
};

// Add more API calls as needed
