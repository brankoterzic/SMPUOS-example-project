#!/bin/bash

#START config service first

cd ConfigurationService && chmod +x build.sh && ./build.sh
cd ../Eureka && chmod +x build.sh && ./build.sh
cd ../PaymentService && chmod +x build.sh && ./build.sh
cd ../ProductService && chmod +x build.sh && ./build.sh
cd ../Ribbon && chmod +x build.sh && ./build.sh
cd ../ShoppingCartService && chmod +x build.sh && ./build.sh
cd ../UserService && chmod +x build.sh && ./build.sh
cd ../Zuul && chmod +x build.sh && ./build.sh
cd ../SpringDataMongoDB && chmod +x build.sh && ./build.sh
