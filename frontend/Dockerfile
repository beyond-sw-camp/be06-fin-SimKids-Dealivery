FROM nginx:latest
RUN ln -snf /usr/share/zoneinfo/Asia/Seoul /etc/localtime
ADD ./default.conf /etc/nginx/conf.d/default.conf
ADD ./dist /usr/share/nginx/html
CMD ["nginx", "-g", "daemon off;"]