<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%--将脚本文件放在页面尾部，从而加快加载速度--%>
<script src="${pageContext.request.contextPath}/resource/js/footer.js"></script>
<script src="${pageContext.request.contextPath}/resource/js/blog.js?v=123"></script>

<footer class="bs-docs-footer">
    <div class="container">
        <div><a href="https://github.com/irocky1/MBlog">GitHub 仓库</a></div>
        <br>
        <p>本项目源码受 <a rel="license" href="https://github.com/twbs/bootstrap/blob/master/LICENSE" target="_blank">MIT </a>开源协议保护，文档受
            <a rel="license" href="https://creativecommons.org/licenses/by/3.0/" target="_blank">CC BY 3.0</a> 开源协议保护。
        </p>
    </div>
</footer>

<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="${pageContext.request.contextPath}/resource/bootstrap/js/bootstrap.min.js"></script>
<script>
    var _hmt = _hmt || [];
    (function() {
        var hm = document.createElement("script");
        hm.src = "https://hm.baidu.com/hm.js?b15115766d9be203be6a98bb016e9622";
        var s = document.getElementsByTagName("script")[0];
        s.parentNode.insertBefore(hm, s);
    })();
</script>

