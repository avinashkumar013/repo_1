// Fetch data from the dummy employee API
fetch("https://dummy.restapiexample.com/api/v1/employees")
  .then(response => {
    if (!response.ok) {
      throw new Error("Network response was not ok");
    }
    return response.json(); // Convert response to JSON
  })
  .then(data => {
    console.log("✅ Employee Data Retrieved Successfully:");
    console.log(data); // Show data in console
  })
  .catch(error => {
    console.error("❌ Error fetching data:", error);
  });
