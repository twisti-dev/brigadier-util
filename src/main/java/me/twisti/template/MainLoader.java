package me.twisti.template;

import io.papermc.paper.plugin.loader.PluginClasspathBuilder;
import io.papermc.paper.plugin.loader.PluginLoader;
import io.papermc.paper.plugin.loader.library.impl.MavenLibraryResolver;
import org.eclipse.aether.artifact.DefaultArtifact;
import org.eclipse.aether.graph.Dependency;
import org.eclipse.aether.repository.RemoteRepository;
import org.jetbrains.annotations.NotNull;

public class MainLoader implements PluginLoader {
    @Override
    public void classloader(@NotNull PluginClasspathBuilder classpathBuilder) {
        MavenLibraryResolver resolver = new MavenLibraryResolver();

        addRepository(resolver, "papermc-repo", "https://repo.papermc.io/repository/maven-public/");

        classpathBuilder.addLibrary(resolver);
    }

    private void addDependency(MavenLibraryResolver resolver, String groupId, String artifactId, String version) {
        resolver.addDependency(new Dependency(new DefaultArtifact("%s:%s:%s".formatted(groupId, artifactId, version)), null));
    }

    private void addRepository(MavenLibraryResolver resolver, String id, String url){
        resolver.addRepository(new RemoteRepository.Builder(id, "default", url).build());
    }
}
