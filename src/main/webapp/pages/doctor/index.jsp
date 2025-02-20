<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <script src="https://unpkg.com/@tailwindcss/browser@4"></script>
        <title>Home</title>
    </head>
    <body>


        <nav class="bg-white border-gray-200 dark:bg-gray-900">
          <div class="max-w-screen-xl flex flex-wrap items-center justify-between mx-auto p-4">
            <a href="/MediCare" class="flex items-center space-x-3 rtl:space-x-reverse">
                <span class="self-center text-2xl font-semibold whitespace-nowrap dark:text-white">MediCare</span>
            </a>
            <button data-collapse-toggle="navbar-default" type="button" class="inline-flex items-center p-2 w-10 h-10 justify-center text-sm text-gray-500 rounded-lg md:hidden hover:bg-gray-100 focus:outline-none focus:ring-2 focus:ring-gray-200 dark:text-gray-400 dark:hover:bg-gray-700 dark:focus:ring-gray-600" aria-controls="navbar-default" aria-expanded="false">
                <span class="sr-only">Open main menu</span>
                <svg class="w-5 h-5" aria-hidden="true" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 17 14">
                    <path stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M1 1h15M1 7h15M1 13h15"/>
                </svg>
            </button>
            <div class="hidden w-full md:block md:w-auto" id="navbar-default">
              <ul class="font-medium flex flex-col p-4 md:p-0 mt-4 border border-gray-100 rounded-lg bg-gray-50 md:flex-row md:space-x-8 rtl:space-x-reverse md:mt-0 md:border-0 md:bg-white dark:bg-gray-800 md:dark:bg-gray-900 dark:border-gray-700">
                <li>
                  <a href="#" class="block py-2 px-3 text-white bg-blue-700 rounded-sm md:bg-transparent md:text-blue-700 md:p-0 dark:text-white md:dark:text-blue-500" aria-current="page">Home</a>
                </li>
                <li>
                  <a href="#" class="block py-2 px-3 text-gray-900 rounded-sm hover:bg-gray-100 md:hover:bg-transparent md:border-0 md:hover:text-blue-700 md:p-0 dark:text-white md:dark:hover:text-blue-500 dark:hover:bg-gray-700 dark:hover:text-white md:dark:hover:bg-transparent">Doctor</a>
                </li>
                <li>
                  <a href="#" class="block py-2 px-3 text-gray-900 rounded-sm hover:bg-gray-100 md:hover:bg-transparent md:border-0 md:hover:text-blue-700 md:p-0 dark:text-white md:dark:hover:text-blue-500 dark:hover:bg-gray-700 dark:hover:text-white md:dark:hover:bg-transparent">Patient</a>
                </li>
              </ul>
            </div>
          </div>
        </nav>

       <div class="container flex justify-between items-center mb-5 py-3 mx-auto">
           <h1 class="text-3xl">All doctors</h1>
           <a href="/MediCare/doctor/create" class="btn bg-blue-700 block py-3 px-6 font-bold text-white rounded">New Patient</a>
       </div>
       <div class="container mx-auto my-4">
           <div class="relative overflow-x-auto">
               <table class="w-full text-sm text-left rtl:text-right text-gray-500 dark:text-gray-400">
                   <thead class="text-xs text-gray-700 uppercase bg-gray-50 dark:bg-gray-700 dark:text-gray-400">
                       <tr>
                           <th scope="col" class="px-6 py-3">
                               #ID
                           </th>
                           <th scope="col" class="px-6 py-3">
                               Doctor name
                           </th>
                           <th scope="col" class="px-6 py-3">
                               username
                           </th>
                           <th scope="col" class="px-6 py-3">
                               Email
                           </th>
                           <th scope="col" class="px-6 py-3">
                               Phone
                           </th>
                           <th scope="col" class="px-6 py-3">
                               Actions
                           </th>
                       </tr>
                   </thead>

                   <tbody>
                        <c:forEach var="doctor" items="${ doctors }">
                            <tr class="bg-white border-b dark:bg-gray-800 dark:border-gray-700 border-gray-200">
                                <th scope="row" class="px-6 py-4 font-medium text-gray-900 whitespace-nowrap dark:text-white">
                                    <c:out value="${doctor.id}" />
                                </th>
                                <th scope="row" class="px-6 py-4 font-medium text-gray-900 whitespace-nowrap dark:text-white">
                                    <c:out value="${doctor.name}" />
                                </th>
                                <td class="px-6 py-4">
                                    <c:out value="${doctor.username}" />
                                </td>
                                <td class="px-6 py-4">
                                    <c:out value="${doctor.email}" />
                                </td>
                                <td class="px-6 py-4">
                                    <c:out value="${doctor.phone}" />
                                </td>
                                <td class="px-6 py-4">
                                    <a href="/MediCare/doctor/appointment?id=<c:out value='${doctor.id}' />">Set appointment</a>
                                    <form action="/MediCare/delete?id=<c:out value='${doctor.id}' />" method="POST">
                                        <button type="submit">Delete</button>
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                   </tbody>
               </table>
           </div>
       </div>

       <footer class="bg-white  shadow-sm dark:bg-gray-900">
            <div class="w-full max-w-screen-xl mx-auto">
                <div class="sm:flex sm:items-center sm:justify-between">
                    <a href="/MediCare" class="flex items-center mb-4 sm:mb-0 space-x-3 rtl:space-x-reverse">
                        <span class="self-center text-2xl font-semibold whitespace-nowrap dark:text-white">MediCare</span>
                    </a>
                    <ul class="flex flex-wrap items-center mb-6 text-sm font-medium text-gray-500 sm:mb-0 dark:text-gray-400">
                        <li>
                            <a href="#" class="hover:underline me-4 md:me-6">Home</a>
                        </li>
                        <li>
                            <a href="#" class="hover:underline me-4 md:me-6">Patients</a>
                        </li>
                        <li>
                            <a href="#" class="hover:underline me-4 md:me-6">Doctors</a>
                        </li>
                    </ul>
                </div>
                <hr class="my-6 border-gray-200 sm:mx-auto dark:border-gray-700 lg:my-8" />
                <span class="block text-sm text-gray-500 sm:text-center dark:text-gray-400">© 2025 <a href="/MediCare" class="hover:underline">MediCare™</a>. All Rights Reserved.</span>
            </div>
       </footer>

    </body>
</html>
